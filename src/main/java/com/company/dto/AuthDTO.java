package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthDTO {
    @NotEmpty(message = "Telefon raqami bosh bolmasligi kerak!")
    private String number;
    @NotEmpty(message = "Parol bosh bolmasligi kerak!")
    private String password;
}
