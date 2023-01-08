package com.solutions.elbaproficiencydemo.repository;

import com.solutions.elbaproficiencydemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CustomBatchRepository<Employee, Integer>, JpaRepository<Employee, Integer> {

    Employee findByName(String name);
}
