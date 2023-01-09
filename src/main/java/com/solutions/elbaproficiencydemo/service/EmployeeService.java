package com.solutions.elbaproficiencydemo.service;

import com.solutions.elbaproficiencydemo.dao.DaoAction;
import com.solutions.elbaproficiencydemo.entity.Employee;
import com.solutions.elbaproficiencydemo.repository.EmployeeRepository;
import com.solutions.elbaproficiencydemo.repository.utils.EntityFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> saveAll(Iterable<Employee> employees) {

        List<Employee> exisitngEmplList = employeeRepository.findAll();
        DaoAction<Employee> daoAction = EntityFilter.filterDuplicateEntities(employees, exisitngEmplList);

        employeeRepository.deleteAllInBatch(daoAction.getRowsToDelete());
        return employeeRepository.saveInBatch(daoAction.getRowsToSave());
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByName(String name) {
        return employeeRepository.findByName(name);
    }
}
