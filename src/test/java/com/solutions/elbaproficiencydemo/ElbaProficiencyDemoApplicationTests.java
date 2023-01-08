package com.solutions.elbaproficiencydemo;

import com.solutions.elbaproficiencydemo.service.DepartmentServiceTest;
import com.solutions.elbaproficiencydemo.service.EmployeeServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {DepartmentServiceTest.class, EmployeeServiceTest.class})
class ElbaProficiencyDemoApplicationTests {

    @Test
    void contextLoads() {
    }

}