package com.solutions.elbaproficiencydemo.repository;


import com.solutions.elbaproficiencydemo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CustomBatchRepository<Department, Integer>, JpaRepository<Department, Integer> {
    Department findByDepName(String name);
}
