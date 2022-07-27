package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SmsDTO {
    private Integer id;
    private String phone;
    private String code;
    private LocalDateTime createdDate;
    private Boolean status;

}
