package com.company.service;


import com.company.dto.CategoryDTO;
import com.company.entity.CategoryEntity;
import com.company.exp.BadRequestException;
import com.company.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public String create(CategoryDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findByKeyAndName(dto.getKey(), dto.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("Bunday kategoriya mavjud");
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setKey(dto.getKey());
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(entity);

        return "Muvaffaqiyatli qo'shildi";
    }
}
