package com.test.TestProject.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
@Table(name = "courses")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "duration")
    private String duration;

    @Column(name = "price")
    private int price;
}
