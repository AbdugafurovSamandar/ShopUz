package com.company.dto;

import com.company.enums.ProfileGender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class RegistrationDTO {
    @NotEmpty(message = "Ism bo‘sh bo‘lmasligi kerak!")
    private String name;
    @NotEmpty(message = "Familya bo‘sh bo‘lmasligi kerak!")
    private String surname;
    @NotEmpty(message = "Telefon raqami kiritilishi majburiy!")
    private String phone;
    @Size(min = 4, message = "Parolning uzunligi 4 ta belgildan yuqori bolsin!")
    private String password;

    private ProfileGender gender;

    private LocalDate birthDate;
    private String jwt;
}
