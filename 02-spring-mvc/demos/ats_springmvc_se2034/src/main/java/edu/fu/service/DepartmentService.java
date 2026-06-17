package edu.fu.service;

import edu.fu.entities.Departments;

import java.util.List;

public interface DepartmentService {
    List<Departments> findAllDepartments();
}
