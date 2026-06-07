package edu.fu.dao;

import edu.fu.entities.Departments;

import java.util.List;

public interface DepartmentInterface {
    Departments findById(long id);
    Departments create (Departments department);
    Departments update (Departments department);
    void  delete (long id);
    List<Departments> findAll();
    Departments findDepartmentByName(String name);
}
