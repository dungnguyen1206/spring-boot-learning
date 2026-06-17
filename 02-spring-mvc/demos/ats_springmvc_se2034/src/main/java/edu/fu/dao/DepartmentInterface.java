package edu.fu.dao;

import edu.fu.entities.Departments;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentInterface {
    Departments findById(long id);
    Departments create (Departments department);
    Departments update (Departments department);
    void  delete (long id);
    List<Departments> findAll();
    Departments findDepartmentByName(String name);
}
