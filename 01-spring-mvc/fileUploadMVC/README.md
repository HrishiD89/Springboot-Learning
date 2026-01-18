# Spring Boot File Upload MVC

A minimal Spring Boot web application demonstrating file upload functionality using Spring MVC and Thymeleaf templates.

## 🚀 Quick Start

```bash
# Clone and run
mvn spring-boot:run
# Access at http://localhost:8080
```

## 📁 Project Overview

This project implements a **single-file upload system** with:
- **5MB file size limit**
- **Filesystem storage** (./uploads/ directory)
- **Security sanitization** (prevents path traversal)
- **Thymeleaf server-side rendering**
- **No database** (simple file storage)

## 🏗️ Architecture

### Tech Stack
- **Spring Boot 4.0.1** - Framework
- **Java 21** - Language
- **Thymeleaf** - Template engine
- **Maven** - Build tool
- **Spring DevTools** - Hot reload

### Key Components
```
src/main/java/com/app/fileuploadmvc/
├── FileUploadMvcApplication.java    # Main app entry point
└── FileUploadController.java        # Web controller with 3 endpoints

src/main/resources/
├── application.properties           # App configuration
├── templates/
│   ├── upload.html                 # Upload form
│   └── uploadStatus.html           # Results page
└── uploads/                        # Runtime storage directory
```

## 🔧 Configuration

### Upload Limits
```properties
# application.properties
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB
```

### Storage Location
- **Directory**: `./uploads/` (auto-created)
- **Path**: Relative to application working directory
- **Security**: Filename sanitization prevents path traversal attacks

## 🎯 Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Upload form page |
| POST | `/upload` | Process file upload |
| GET | `/uploadStatus` | Show upload results |

## 🛡️ Security Features

- **Path traversal protection** via filename sanitization
- **File size validation** (5MB max)
- **Empty file detection**
- **Automatic directory creation** with proper permissions

## 🚀 Development

```bash
# Run development server
mvn spring-boot:run

# Build production JAR
mvn clean package

# Run JAR
java -jar target/fileuploadmvc-0.0.1-SNAPSHOT.jar
```

## 📝 Usage Example

1. Start the application: `mvn spring-boot:run`
2. Open browser: `http://localhost:8080`
3. Select a file to upload (max 5MB)
4. View upload status and results

## 🔍 File Upload Code Pattern

### Controller Pattern
```java
@PostMapping("/upload")
public String handleFileUpload(@RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes) {
    // 1. Validate file
    // 2. Sanitize filename
    // 3. Save to filesystem
    // 4. Add flash attributes for status
    return "redirect:/uploadStatus";
}
```

### File Storage Logic
```java
// Safe filename handling
String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
Files.copy(file.getInputStream(), uploadPath.resolve(originalFilename));
```

## 📚 Resources for Spring Boot File Handling

### Official Documentation
- **Spring Boot File Upload Guide**: https://spring.io/guides/gs/uploading-files/
- **Spring MVC Multipart Guide**: https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-multipart
- **Thymeleaf Documentation**: https://www.thymeleaf.org/documentation.html

### Advanced Topics
- **Large File Uploads**: Use streaming with `MultipartFile.transferTo()`
- **Multiple File Uploads**: Use `@RequestParam("files") MultipartFile[] files`
- **Database Storage**: Store files as BLOB in database
- **Cloud Storage**: Integrate with AWS S3, Google Cloud Storage
- **Asynchronous Processing**: Use `@Async` for large files
- **Progress Tracking**: Implement progress bars with WebSocket/SSE

### Security Best Practices
- **File Type Validation**: Check MIME types and extensions
- **Virus Scanning**: Integrate with antivirus APIs
- **Rate Limiting**: Implement upload frequency limits
- **Content Security Policy**: Configure CSP headers

### Production Considerations
- **External Storage**: Move uploads outside application directory
- **Load Balancing**: Use shared storage for multiple instances
- **Monitoring**: Track upload metrics and errors
- **Cleanup**: Implement automated file cleanup policies

## 🔄 Extending This Project

### Quick Additions
- **Multiple file upload**: Change form to `multiple` attribute
- **File type validation**: Add extension checking
- **User authentication**: Add Spring Security
- **Database storage**: Add JPA entities for file metadata

### Advanced Extensions
- **Drag & drop UI**: Replace form with modern JavaScript
- **Progress bars**: Add real-time upload progress
- **File preview**: Show thumbnails before upload
- **Cloud integration**: Add AWS S3 or similar

## 🐛 Troubleshooting

**File too large**: Increase `max-file-size` in application.properties
**Permission denied**: Ensure write permissions on uploads directory
**File not found**: Check if uploads directory was created automatically

---

*This is a minimal implementation for learning purposes. For production use, consider adding comprehensive validation, security, and monitoring.*