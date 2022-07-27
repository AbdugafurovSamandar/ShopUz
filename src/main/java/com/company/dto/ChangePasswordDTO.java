package com.company.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
