package org.app.jobportal;

import jakarta.annotation.PostConstruct;
import org.app.jobportal.model.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    List<Job> jobsList;

    @PostConstruct
    public void init() {
        Job job1 = new Job("1", "Software Engineer", "Develop software applications", LocalDate.now());
        Job job2 = new Job("2", "Data Scientist", "Analyze data and develop models", LocalDate.now());
        jobsList = new ArrayList<Job>();
        jobsList.add(job1);
        jobsList.add(job2);
    }
    @GetMapping("/jobs")
    public List<Job> getJob(){
        for(Job j : jobsList){
            j.setLastAccessDate(LocalDateTime.now());
            j.addNumViews();
        }
        return jobsList;
    }

    @GetMapping("/jobs/search/{query}")
    public List<Job> searchJobs(@PathVariable String query){
        List<Job> matchingJobs = new ArrayList<>();
        for(Job j : jobsList){
            if(j.getTitle().contains(query.toLowerCase()) || j.getDescription().contains(query.toLowerCase())){
                matchingJobs.add(j);
            }
        }
        return matchingJobs;
    }

}
