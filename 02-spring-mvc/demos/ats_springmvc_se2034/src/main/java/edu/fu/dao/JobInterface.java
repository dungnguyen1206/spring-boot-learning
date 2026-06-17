package edu.fu.dao;

import edu.fu.entities.Jobs;

import java.util.List;

public interface JobInterface {
    Jobs findById(Long id);
    List<Jobs> findAll();
    Jobs save(Jobs job);
    void delete(Jobs job);
    Jobs update(Jobs job);

    boolean isExist (String title);

}
