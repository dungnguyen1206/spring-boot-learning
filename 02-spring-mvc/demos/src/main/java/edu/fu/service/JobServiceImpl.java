package edu.fu.service;

import edu.fu.dao.JobRepository;
import edu.fu.dto.JobRequest;
import edu.fu.entities.Departments;
import edu.fu.entities.Jobs;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Jobs> findAll() {
            return jobRepository.findAll();
    }

    @Override
    public Jobs findById(Long id) {
        if(id==null){
            throw new IllegalArgumentException("The id must not be null");
        }
        return jobRepository.findById(id);
    }

    @Override
    public Jobs save(JobRequest job) {
        if(job.getDeadline().compareTo(Instant.now())<0){
            throw new IllegalArgumentException("The deadline must be after current time");
        }
        if(job.getMinSalary() >= job.getMaxSalary()){
            throw new IllegalArgumentException("The maximum salary must be greater than the minimum salary");
        }
        if(jobRepository.isExist(job.getTitle())){
            throw new IllegalArgumentException("The title already exists");
        }
        return jobRepository.save(fromDto(job));
    }

    public Jobs fromDto(JobRequest job) {
        Jobs jobs = new Jobs();
        jobs.setTitle(job.getTitle());
        jobs.setDeadline(job.getDeadline());
        jobs.setUtm_source(job.getUtmSource());
        jobs.setDescription(job.getDescription());
        jobs.setSalary_max(job.getMaxSalary());
        jobs.setSalary_min(job.getMinSalary());
        if(job.getDepartmentId()!=null){
            Departments departments = new Departments();
            departments.setId(job.getDepartmentId());
            jobs.setDepartment(departments);
        }
        jobs.setLocation(job.getLocation());
        jobs.setStatus(job.getStatus());
        jobs.setUtm_source(job.getUtmSource());
        jobs.setUtm_medium(job.getUtmMedia());
        return jobs;
    }
}
