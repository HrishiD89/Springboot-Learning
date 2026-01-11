package org.app.jobportal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {
    @GetMapping("/")
    public String listJobs(){
        return "<h1>Here are the list of jobs</h1>";
    }

}
