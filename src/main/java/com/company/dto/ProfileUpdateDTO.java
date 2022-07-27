package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileUpdateDTO {

    private String name;
    private String surname;
    private LocalDate birthDate;

}
