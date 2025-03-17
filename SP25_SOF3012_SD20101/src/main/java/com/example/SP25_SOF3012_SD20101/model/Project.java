package com.example.SP25_SOF3012_SD20101.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "du_an")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "phan_chia_cong_viec",
            joinColumns = @JoinColumn(name = "du_an_id"),
            inverseJoinColumns = @JoinColumn(name = "nhan_vien_id")
    )
    private List<Employee> employees;
}
