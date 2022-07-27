package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String key;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate;

}
