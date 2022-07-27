package com.company.controller;

import com.company.dto.RegionDTO;
import com.company.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/adm/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO dto) {
        regionService.create(dto);
        return ResponseEntity.ok().body("Success");
    }

}
