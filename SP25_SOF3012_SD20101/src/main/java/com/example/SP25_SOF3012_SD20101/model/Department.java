package com.example.SP25_SOF3012_SD20101.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity// Đánh dấu thực thể Entity
@Table(name = "phong_ban") // Map với bảng phong_ban trong csdl
public class Department {

    @Id // Đánh dấu khóa chính
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    //mappedBy trỏ tới biến department trong class Employee
    private List<Employee> employees;
}
