package com.company.service;

import com.company.dto.RegionDTO;
import com.company.entity.RegionEntity;
import com.company.exp.BadRequestException;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public void create(RegionDTO dto) {

        Optional<RegionEntity> optional = regionRepository.findByKey(dto.getKey());
        if (optional.isPresent()) {
            throw new BadRequestException("Region already exists");
        }

        RegionEntity entity = new RegionEntity();
        entity.setKey(dto.getKey());
        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());

        regionRepository.save(entity);

    }
}
