package com.company.service;

import com.company.dto.DistrictDTO;
import com.company.entity.DistrictEntity;
import com.company.exp.BadRequestException;
import com.company.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public void create(DistrictDTO dto) {
        String key = "_tumani";
        Optional<DistrictEntity> optional = districtRepository.findByKeyAndName(dto.getKey(), dto.getName());
        if (optional.isPresent()) {
            throw new BadRequestException("Bunday tuman mavjud");
        }
        DistrictEntity entity = new DistrictEntity();
        entity.setName(dto.getName());
        entity.setKey(dto.getKey());
        entity.setRegionId(dto.getRegionId());
        entity.setCreatedDate(LocalDateTime.now());
        districtRepository.save(entity);

        return;
    }
}
