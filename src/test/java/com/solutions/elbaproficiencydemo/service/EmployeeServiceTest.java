package com.solutions.elbaproficiencydemo.service;

import com.solutions.elbaproficiencydemo.entity.Employee;
import com.solutions.elbaproficiencydemo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private static EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    private static List<Employee> employeeList = new ArrayList<>();

    @BeforeAll
    static void setUp() {

        Employee emp1 = new Employee.Builder()
                .setName("Peg Legge")
                .setManager("liz.erd")
                .setUsername("peg.legge")
                .setEmail("peg.legge@gmail.com")
                .setDepartment("Sales")
                .setPhoneNumber("(566) 576-7810")
                .setAddress("Prishtine L Dardania")
                .setStartDate(LocalDate.of(2020, 5, 13))
                .setEndDate(LocalDate.of(2024, 10, 11)).build();

        Employee emp2 = new Employee.Builder()
                .setName("Ray Sin")
                .setManager("lee.sun")
                .setUsername("ray.sin")
                .setEmail("ray.sin@gmail.com")
                .setDepartment("Sales")
                .setPhoneNumber("(566) 576-7821")
                .setAddress("Prishtine L Dardania")
                .setStartDate(LocalDate.of(2020, 5, 13))
                .setEndDate(LocalDate.of(2022, 10, 11)).build();

        Employee emp3 = new Employee.Builder()
                .setName("Isabelle Ringing")
                .setManager("lee.sun")
                .setUsername("isabelle.ringing")
                .setEmail("isabelle.ringing@gmail.com")
                .setDepartment("Sales")
                .setPhoneNumber("(566) 576-7810")
                .setAddress("Prishtine L Dardania")
                .setStartDate(LocalDate.of(2020, 5, 13))
                .setEndDate(LocalDate.of(2022, 10, 11)).build();

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
    }

    @Test
    public void employeesCanBeFoundByName() {

        given(employeeRepository.findByName(employeeList.get(0).getName()))
                .willReturn(employeeList.get(0));

        Employee empFound = employeeService.findByName(employeeList.get(0).getName());

        assertThat(empFound).isEqualTo(employeeList.get(0));
        assertThat(empFound.isActive()).isEqualTo(true);
    }

    @Test
    public void employeeCannotBeFoundByWrongName() {
        given(employeeRepository.findByName("wrong name"))
                .willReturn(null);

        Employee empFound = employeeService.findByName("wrong name");

        assertThat(empFound).isNull();
    }

    @Test
    @Order(5)
    public void allEmployeesCanBeFound() {
        given(employeeRepository.findAll())
                .willReturn(employeeList);

        List<Employee> empListFound = employeeService.findAll();

        assertThat(empListFound.size()).isEqualTo(3);
    }

    @Test
    @Order(1)
    public void listOfEmployeesCanBeSaved() {

        when(employeeRepository.saveInBatch(any())).thenReturn(employeeList);

        List<Employee> savedEmployeeList = employeeService.saveAll(employeeList);

        assertThat(savedEmployeeList).isNotNull();
        assertThat(savedEmployeeList.size()).isEqualTo(3);
    }
}
