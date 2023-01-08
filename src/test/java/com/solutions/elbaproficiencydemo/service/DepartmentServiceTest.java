package com.solutions.elbaproficiencydemo.service;

import com.solutions.elbaproficiencydemo.entity.Department;
import com.solutions.elbaproficiencydemo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private static DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;


    private static List<Department> depeartmentList = new ArrayList<>();

    @BeforeAll
    static void setUp() {

        Department dep1 = new Department.Builder()
                .setDepName("Dev")
                .setDepLeaderName("liz.erd")
                .setDepPhoneNumber("383 44 123 123").build();

        Department dep2 = new Department.Builder()
                .setDepName("Dev")
                .setDepLeaderName("liz.erd")
                .setDepPhoneNumber("383 44 123 123").build();

        Department dep3 = new Department.Builder()
                .setDepName("Dev")
                .setDepLeaderName("liz.erd")
                .setDepPhoneNumber("383 44 123 123").build();

        depeartmentList.add(dep1);
        depeartmentList.add(dep2);
        depeartmentList.add(dep3);
    }

    @Test
    public void employeesCanBeFoundByName() {

        given(departmentRepository.findByDepName(depeartmentList.get(0).getDepName()))
                .willReturn(depeartmentList.get(0));

        Department depFound = departmentService.findByName(depeartmentList.get(0).getDepName());

        assertThat(depFound).isEqualTo(depeartmentList.get(0));
    }

    @Test
    public void employeeCannotBeFoundWithWrongName() {
        given(departmentRepository.findByDepName("wrong name"))
                .willReturn(null);

        Department depFound = departmentService.findByName("wrong name");

        assertThat(depFound).isNull();
    }

    @Test
    @Order(5)
    public void allEmployeesCanBeFound() {
        given(departmentRepository.findAll())
                .willReturn(depeartmentList);

        List<Department> empListFound = departmentService.findAll();

        assertThat(empListFound.size()).isEqualTo(3);
    }

    @Test
    @Order(1)
    public void listOfDepartmentCanBeSaved() {

        when(departmentRepository.saveInBatch(any())).thenReturn(depeartmentList);

        List<Department> savedDepartmentList = departmentService.saveAll(depeartmentList);

        assertThat(savedDepartmentList).isNotNull();
        assertThat(savedDepartmentList.size()).isEqualTo(3);
    }
}
