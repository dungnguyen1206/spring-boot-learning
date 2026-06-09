package edu.fu.service;

import edu.fu.dto.JobRequest;
import edu.fu.entities.Jobs;

import java.util.List;

public interface JobService {
    public List<Jobs> findAll();
    public Jobs findById(Long id);
    public Jobs save(JobRequest job);
}
