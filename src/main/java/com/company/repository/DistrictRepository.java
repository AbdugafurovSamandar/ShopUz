package com.company.repository;

import com.company.entity.DistrictEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DistrictRepository extends CrudRepository<DistrictEntity, Integer> {
    Optional<DistrictEntity> findByKeyAndName(String key, String name);
}
