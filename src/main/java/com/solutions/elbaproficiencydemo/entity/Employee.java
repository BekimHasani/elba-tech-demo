package com.solutions.elbaproficiencydemo.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @SequenceGenerator(name = "employee_id_generator", sequenceName = "employee_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_generator")
    private int id;
    private String name;
    private String manager;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean isActive;
    private LocalDate startDate;
    private LocalDate endDate;
    private String department;

    private Employee(Builder employeeBuilder) {
        this.name = employeeBuilder.name;
        this.manager = employeeBuilder.manager;
        this.username = employeeBuilder.username;
        this.email = employeeBuilder.email;
        this.phoneNumber = employeeBuilder.phoneNumber;
        this.address = employeeBuilder.address;
        this.isActive = employeeBuilder.isActive;
        this.startDate = employeeBuilder.startDate;
        this.endDate = employeeBuilder.endDate;
        this.department = employeeBuilder.department;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }


    public int getId() {
        return id;
    }

    public String getManager() {
        return manager;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDepartment() {
        return department;
    }

    public boolean isActive() {
        return isActive;
    }

    public Employee update(Employee employee) {
        manager = employee.manager;
        username = employee.username;
        email = employee.email;
        phoneNumber = employee.phoneNumber;
        address = employee.address;
        isActive = endDate.isAfter(LocalDate.now());
        startDate = employee.startDate;
        endDate = employee.endDate;
        department = employee.department;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static class Builder {

        // required parameters
        private String name;
        private String manager;
        private String username;
        private String email;
        private String phoneNumber;
        private String address;
        private boolean isActive;
        private LocalDate startDate;
        private LocalDate endDate;
        private String department;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setManager(String manager) {
            this.manager = manager;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Employee build() {
            isActive = (endDate.isAfter(LocalDate.now())) ? true : false;
            return new Employee(this);
        }
    }
}
