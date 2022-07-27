package com.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseInfoDTO {
    private int status;
    private String message;

    public ResponseInfoDTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseInfoDTO(int status) {
        this.status = status;
    }
}
