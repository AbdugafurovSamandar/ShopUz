package com.company.dto;

import com.company.enums.ProfileGender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

    private String name;

    private String surname;

    private String phone;

    private String password;

    private ProfileGender gender;
    private Double balance;
    private LocalDate birthDate;
    private ProfileRole role;
    private ProfileStatus status;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String jwt;
}
