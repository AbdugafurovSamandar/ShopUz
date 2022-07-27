package com.company.controller;

import com.company.dto.DistrictDTO;
import com.company.dto.RegionDTO;
import com.company.service.DistrictService;
import com.company.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody DistrictDTO dto) {
        districtService.create(dto);
        return ResponseEntity.ok().body("Success");
    }
}
