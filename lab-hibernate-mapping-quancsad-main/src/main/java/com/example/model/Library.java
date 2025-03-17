package com.example.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "library")
public class Library {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;
    @OneToMany(mappedBy = "library")
    private List<Book> book;
}
