package com.company.service;


import com.company.dto.SmsDTO;
import com.company.entity.SmsHistoryEntity;
import com.company.repository.SmsHistoryRepository;
import com.company.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SmsService {
    @Value("${sms.url}")
    private String smsUrl;
    @Value("${sms.key}")
    private String key;
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;


    public void sendRegistrationSms(String phone) {
        String code = RandomUtil.getRandomSmsCode();
        String message = "Kun.uz Test partali uchun\n registratsiya kodi: " + code;

//        SmsResponseDTO responseDTO = send(phone, message);

        SmsHistoryEntity entity = new SmsHistoryEntity();
        entity.setPhone(phone);
        entity.setCode(code);
        entity.setStatus(Boolean.TRUE);

        smsHistoryRepository.save(entity);
    }

    public boolean verifySms(String phone, String code) {
        Optional<SmsHistoryEntity> optional = smsHistoryRepository.findTopByPhoneOrderByCreatedDateDesc(phone);
        if (optional.isEmpty()) {
            return false;
        }
        SmsHistoryEntity sms = optional.get();
        LocalDateTime validDate = sms.getCreatedDate().plusMinutes(1);

        if (sms.getCode().equals(code) && validDate.isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    public Long getSmsCount(String phone) {
        return smsHistoryRepository.getSmsCount(phone);
    }

    public PageImpl pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<SmsHistoryEntity> all = smsHistoryRepository.findAll(pageable);

        List<SmsHistoryEntity> list = all.getContent();

        List<SmsDTO> dtoList = new LinkedList<>();

        list.forEach(entity -> {
            SmsDTO dto = new SmsDTO();
            dto.setId(entity.getId());
            dto.setPhone(entity.getPhone());
            dto.setCode(entity.getCode());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setStatus(entity.getStatus());

            dtoList.add(dto);
        });

        return new PageImpl(dtoList, pageable, all.getTotalElements());
    }
}

//    private SmsResponseDTO send(String phone, String message) {
//        SmsRequestDTO requestDTO = new SmsRequestDTO();
//        requestDTO.setKey(key);
//        requestDTO.setPhone(phone);
//        requestDTO.setMessage(message);
//        System.out.println("Sms Request: message " + message);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<SmsRequestDTO> entity = new HttpEntity<SmsRequestDTO>(requestDTO, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        SmsResponseDTO response = restTemplate.postForObject(smsUrl, entity, SmsResponseDTO.class);
//        System.out.println("Sms Response  " + response);
//        return response;
//    }
