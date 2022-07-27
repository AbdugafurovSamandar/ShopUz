package com.company.repository;

import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByPhone(String phone);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity p SET p.status=?2 WHERE p.phone=?1")
    void updateStatusByPhone(String phone, ProfileStatus status);
}
