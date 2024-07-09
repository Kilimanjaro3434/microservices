package com.app.crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String first_name;
    @Column(name = "surname")
    private String last_name;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;
}
