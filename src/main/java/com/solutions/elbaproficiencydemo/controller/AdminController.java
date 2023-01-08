package com.solutions.elbaproficiencydemo.controller;

import com.solutions.elbaproficiencydemo.entity.Department;
import com.solutions.elbaproficiencydemo.entity.Employee;
import com.solutions.elbaproficiencydemo.service.DepartmentService;
import com.solutions.elbaproficiencydemo.service.EmployeeService;
import com.solutions.elbaproficiencydemo.service.ExcelObjectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ExcelObjectsService excelObjectsService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public AdminController(ExcelObjectsService excelObjectsService,
                           EmployeeService employeeService, DepartmentService departmentService) {
        this.excelObjectsService = excelObjectsService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @PutMapping("/import")
    public ResponseEntity<String> saveObjects(@RequestParam(value = "reloadFile", required = false) boolean reloadFile) {
        excelObjectsService.generateExcelObjects(reloadFile);
        return new ResponseEntity<>("Entities imported successfully ", HttpStatus.OK);
    }

    @GetMapping("/employee/all")
    public ResponseEntity<List<Employee>> findAllEmployees() {
        List<Employee> foundedEmployees = employeeService.findAll();
        return new ResponseEntity<>(foundedEmployees, HttpStatus.OK);
    }

    @GetMapping("/employee/{name}")
    public ResponseEntity<Employee> findEmployeeByName(@PathVariable(value = "name") String name) {
        Employee foundedEmployee = employeeService.findByName(name);
        return new ResponseEntity<>(foundedEmployee, HttpStatus.OK);
    }

    @GetMapping("/department/all")
    public ResponseEntity<List<Department>> findAllDepartment() {
        List<Department> foundedDepartments = departmentService.findAll();
        return new ResponseEntity<>(foundedDepartments, HttpStatus.OK);
    }
}
