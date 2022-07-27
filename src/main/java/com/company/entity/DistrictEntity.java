package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "district")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String key;

    @Column(nullable = false)
    private String name;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false, updatable = false, insertable = false)
    private RegionEntity region;


    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate;

}
