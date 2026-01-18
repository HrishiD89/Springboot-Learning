package com.app.fileuploadmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    private static final Path UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "uploads");

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file == null || file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/uploadStatus";
        }

        // Some browsers can send a full path; keep only the file name part
        String original = file.getOriginalFilename();
        String safeFileName = (original == null) ? "upload.bin" : Paths.get(original).getFileName().toString();

        try {
            // Ensure uploads/ exists (fixes NoSuchFileException)
            Files.createDirectories(UPLOAD_DIR);

            Path target = UPLOAD_DIR.resolve(safeFileName).normalize();

            // Safety check: prevent paths like ../../something
            if (!target.startsWith(UPLOAD_DIR)) {
                redirectAttributes.addFlashAttribute("message", "Invalid file name.");
                return "redirect:/uploadStatus";
            }

            // Write to disk (creates/overwrites the file)
            file.transferTo(target.toFile());

            redirectAttributes.addFlashAttribute(
                    "message",
                    "You successfully uploaded '" + safeFileName + "'"
            );
            return "redirect:/uploadStatus";
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Upload failed: " + e.getMessage());
            return "redirect:/uploadStatus";
        }
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}