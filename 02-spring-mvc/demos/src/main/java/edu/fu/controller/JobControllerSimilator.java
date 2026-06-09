package edu.fu.controller;

import edu.fu.dto.JobRequest;
import edu.fu.entities.Jobs;
import edu.fu.enums.JobStatus;
import edu.fu.service.JobService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;

public class JobControllerSimilator {
    public static void main(String[] args) {
        JobRequest jobRequest = new JobRequest();

        jobRequest.setTitle("Intern Java 2026");

        jobRequest.setDescription("""
                We are looking for a Fresher Java  Developer to build scalable microservices 
                for an EdTech platform. The candidate will work with Spring Boot, PostgreSQL, Docker, 
                and Kubernetes in an Agile environment.
                """);

        jobRequest.setLocation("Ha Noi, Viet Nam");

        jobRequest.setMinSalary(25000000.00);
        jobRequest.setMaxSalary(45000000.00);

        jobRequest.setStatus(JobStatus.DRAFT.toString());
        jobRequest.setUtmSource("linkedin");
        jobRequest.setUtmMedia("social-media");
        jobRequest.setDeadline(Instant.parse("2026-06-30T23:59:59Z"));

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("edu.fu");
        JobService jobService = (JobService) applicationContext.getBean("jobService", JobService.class);
        Jobs result = jobService.save(jobRequest);
    }
}
