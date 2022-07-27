package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sellers")
public class SellersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column
    private String name;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
