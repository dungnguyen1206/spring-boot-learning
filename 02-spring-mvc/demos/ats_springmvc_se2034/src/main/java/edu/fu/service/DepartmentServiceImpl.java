package edu.fu.service;

import edu.fu.dao.DepartmentInterface;
import edu.fu.dao.DepartmentRepository;
import edu.fu.entities.Departments;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentInterface departmentRepository;

    @Override
    public List<Departments> findAllDepartments() {
        return departmentRepository.findAll();
    }
}
