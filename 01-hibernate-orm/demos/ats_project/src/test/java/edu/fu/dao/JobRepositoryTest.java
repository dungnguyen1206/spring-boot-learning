package edu.fu.dao;

import edu.fu.entities.Jobs;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobRepositoryTest {
    private static JobRepository jobRepository;
    private static EntityManager em;
    @BeforeAll
    public static void setUp(){
      jobRepository = new JobRepository();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        List<Jobs> jobs = jobRepository.findAll();
        Assertions.assertNotNull(jobs);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}