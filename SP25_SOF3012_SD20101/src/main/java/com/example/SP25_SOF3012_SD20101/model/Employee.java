package com.example.SP25_SOF3012_SD20101.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "nhan_vien")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "phong_ban_id")//Đổi tên cột
    private Department department;

    @ManyToMany(mappedBy = "employees")
    private List<Project> projects;
}
