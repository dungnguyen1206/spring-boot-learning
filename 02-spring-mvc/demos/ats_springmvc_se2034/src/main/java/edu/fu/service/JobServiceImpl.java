package edu.fu.service;

import edu.fu.dao.JobInterface;
import edu.fu.dao.JobRepository;
import edu.fu.dto.JobRequest;
import edu.fu.entities.Departments;
import edu.fu.entities.Jobs;
import edu.fu.enums.JobStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobInterface jobService;

    @Override
    public List<Jobs> findAll() {
            return jobService.findAll();
    }

    @Override
    public Jobs findById(Long id) {
        if(id==null){
            throw new IllegalArgumentException("The id must not be null");
        }
        return jobService.findById(id);
    }

    @Override
    public Jobs save(JobRequest job) {
        if(job.getDeadline().compareTo(LocalDateTime.now())<0){
            throw new IllegalArgumentException("The deadline must be after current time");
        }
        if(job.getMinSalary() >= job.getMaxSalary()){
            throw new IllegalArgumentException("The maximum salary must be greater than the minimum salary");
        }
        if(jobService.isExist(job.getTitle())){
            throw new IllegalArgumentException("The title already exists");
        }
        return jobService.save(fromDto(job));
    }

    public Jobs fromDto(JobRequest job) {
        Jobs jobs = new Jobs();
        jobs.setTitle(job.getTitle());
        jobs.setDeadline(job.getDeadline().atZone(ZoneId.systemDefault()).toInstant());
        jobs.setUtm_source(job.getUtmSource());
        jobs.setDescription(job.getDescription());
        jobs.setMaxSalary(job.getMaxSalary());
        jobs.setMinSalary(job.getMinSalary());
        if(job.getDepartmentId()!=null){
            Departments departments = new Departments();
            departments.setId(job.getDepartmentId());
            jobs.setDepartment(departments);
        }
        jobs.setLocation(job.getLocation());
        jobs.setStatus(job.getStatus());
        jobs.setUtm_source(job.getUtmSource());
        jobs.setUtm_medium(job.getUtmMedia());
        jobs.setStatus(JobStatus.DRAFT.toString());
        return jobs;
    }
}
