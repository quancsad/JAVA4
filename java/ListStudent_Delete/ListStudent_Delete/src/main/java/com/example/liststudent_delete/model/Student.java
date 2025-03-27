package com.example.liststudent_delete.model;


import jakarta.persistence.*;

@Entity

@Table(name = "hoc_sinh")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 1 ;
    @Column(name = "ten_hoc_sinh")
    private String name;



    @Column(name = "diem_toan")
    private float mathScore;

    @Column(name = "ngay_sinh")
    private String dob ;



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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
