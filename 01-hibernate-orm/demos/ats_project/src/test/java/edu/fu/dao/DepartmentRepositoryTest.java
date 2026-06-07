package edu.fu.dao;

import edu.fu.entities.Departments;
import edu.fu.utils.DbContext;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DepartmentRepositoryTest {

    private static DepartmentRepository departmentRepository;
    private static EntityManager em;
    @BeforeAll
    public static void setUp(){
      departmentRepository = new DepartmentRepository();
    }
    @Test
    public void findById() {
        //Input
        Long id =1L;

        //Actual Result
        Departments actual = departmentRepository.findById(id);

        //compare
        Assertions.assertNotNull(actual);

    }

    @Test
    public void save(){

    }

    @Test
    void findByName(){
        String name = "JPD";
        Departments departments = departmentRepository.findDepartmentByName(name);
        Assertions.assertNotNull(departments);
    }
    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}
