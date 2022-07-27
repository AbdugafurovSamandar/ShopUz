package com.company.entity;

import com.company.enums.ProfileGender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "profile")

public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false,unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column
    private Double balance = 0.0;

    @Enumerated(EnumType.STRING)
    private ProfileGender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private Boolean visible = Boolean.TRUE;

    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


}
