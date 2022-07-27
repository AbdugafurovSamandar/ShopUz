package com.company.repository;

import com.company.entity.SmsHistoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface SmsHistoryRepository extends PagingAndSortingRepository<SmsHistoryEntity, Integer> {

    Optional<SmsHistoryEntity> findTopByPhoneOrderByCreatedDateDesc(String phone);

    @Query(value = "select count(*) from sms_history_entity where phone =:phone and created_date > now() - INTERVAL '1 MINUTE' ",
            nativeQuery = true)
    Long getSmsCount(@Param("phone") String phone);

//    @Modifying
//    @Transactional
//    @Query("UPDATE ProfileEntity set status=:status where id=:id")
//    void changeStatus(@Param("status") ProfileStatus status, @Param("id") Integer id);
}
