package com.solutions.elbaproficiencydemo.service;

import com.solutions.elbaproficiencydemo.entity.Department;
import com.solutions.elbaproficiencydemo.entity.Employee;
import com.solutions.elbaproficiencydemo.service.exclFile.ExcelReaderService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelObjectsService {
    private final ExcelReaderService excelReader;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    public ExcelObjectsService(ExcelReaderService excelReader ,
                               EmployeeService employeeService,
                               DepartmentService departmentService){
        this.excelReader = excelReader;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    public void generateExcelObjects(boolean reloadFile){
        List<List<String>> objects = excelReader.xlsxFileReader(reloadFile);

        List<Employee> employees = new ArrayList<>();
        List<Department> departments = new ArrayList<>();

        for (List<String> param : objects) {
            String [] parameters = param.toArray(String[] ::new);

            try {
                if(parameters.length != 0)
                    employees.add(createEmployeeObj(parameters));
                if(parameters.length > 9 )
                    departments.add(createDepartmentObj(parameters));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        employeeService.saveAll(employees);
        departmentService.saveAll(departments);
    }

    private Employee createEmployeeObj(String[] parameters) throws ParseException {
        return new Employee.Builder()
                .setName(parameters[0])
                .setManager(parameters[1])
                .setUsername(parameters[2])
                .setEmail(parameters[3])
                .setDepartment(parameters[4])
                .setPhoneNumber(parameters[5])
                .setAddress(parameters[6])
                .setStartDate(dateParser(parameters[7]))
                .setEndDate(dateParser(parameters[8])).build();
    }

    private Department createDepartmentObj(String[] parameters) throws ParseException {
        return new Department.Builder()
                .setDepName(parameters[11])
                .setDepLeaderName(parameters[12])
                .setDepPhoneNumber(parameters[13]).build();
    }

    private LocalDate dateParser(String date) {
        int yyyy = Integer.parseInt(date.substring(0,4));
        int mm = Integer.parseInt(date.substring(4,6));
        int dd = Integer.parseInt(date.substring(6, 8));
        return LocalDate.of(yyyy, mm, dd);
    }
}
