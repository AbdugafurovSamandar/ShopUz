package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DistrictDTO {
    private String name;
    private String key;
    private Integer regionId;
}
