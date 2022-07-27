package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/adm/create")
    public ResponseEntity<String> create(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }
}
