package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String key;

    @Column
    private String name;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
