package com.example.liststudent_delete.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity

@Table(name = "hoc_sinh")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "ten_hoc_sinh")
    private String name;

    @Column(name = "diem_toan")
    private float mathScore;

    @Column(name = "ngay_sinh")
    private Date dob ;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMathScore() {
        return mathScore;
    }

    public void setMathScore(float mathScore) {
        this.mathScore = mathScore;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
