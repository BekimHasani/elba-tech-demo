package com.solutions.elbaproficiencydemo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Department{
    @Id
    @SequenceGenerator(name = "department_id_generator",  sequenceName = "department_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_generator")
    private Long id;
    private String depName;
    private String depLeader;
    private String depPhoneNumber;

    private Department(Builder departmentBuilder){
        this.depName = departmentBuilder.depName;
        this.depLeader = departmentBuilder.depLeader;
        this.depPhoneNumber = departmentBuilder.depPhoneNumber;
    }

    public Department() {

    }

    public String getDepName() {
        return depName;
    }

    public Long getId() {
        return id;
    }

    public String getDepLeader() {
        return depLeader;
    }

    public String getDepPhoneNumber() {
        return depPhoneNumber;
    }

    public Department update(Department department){
        depName = department.depName;
        depLeader = department.depLeader;
        depPhoneNumber = department.depPhoneNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(depName, that.depName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depName);
    }

    public static class Builder{
        private String depName;
        private String depLeader;
        private String depPhoneNumber;

        public Builder setDepName(String name){
            this.depName = name;
            return this;
        }
        public Builder setDepLeaderName(String leader){
            this.depLeader = leader;
            return this;
        }
        public Builder setDepPhoneNumber(String phoneNumber){
            this.depPhoneNumber = phoneNumber;
            return this;
        }

        public Department build(){
            return new Department(this);
        }
    }
}
