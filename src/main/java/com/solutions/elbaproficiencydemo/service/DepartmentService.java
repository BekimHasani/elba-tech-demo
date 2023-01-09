package com.solutions.elbaproficiencydemo.service;

import com.solutions.elbaproficiencydemo.dao.DaoAction;
import com.solutions.elbaproficiencydemo.entity.Department;
import com.solutions.elbaproficiencydemo.repository.DepartmentRepository;
import com.solutions.elbaproficiencydemo.repository.utils.EntityFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> saveAll(Iterable<Department> departments) {

        List<Department> exisitngDeplList = departmentRepository.findAll();
        DaoAction daoAction = EntityFilter.filterDuplicateEntities(departments, exisitngDeplList);

        departmentRepository.deleteAllInBatch(daoAction.getRowsToDelete());
        return departmentRepository.saveInBatch(daoAction.getRowsToSave());
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findByName(String wrongName) {
        return departmentRepository.findByDepName(wrongName);
    }
}
