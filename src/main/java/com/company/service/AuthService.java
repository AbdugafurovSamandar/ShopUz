package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.*;
import com.company.entity.ProfileEntity;
import com.company.entity.SmsHistoryEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.ProfileAlreadyExists;
import com.company.repository.ProfileRepository;
import com.company.repository.SmsHistoryRepository;
import com.company.util.JwtUtil;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public void registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByPhone(dto.getPhone());
        if (optional.isPresent()) {
            throw new ProfileAlreadyExists("Bunday raqam mavjud");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        profileRepository.save(entity);
        smsService.sendRegistrationSms(dto.getPhone());
    }

    public ProfileDTO login(AuthDTO authDTO) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authDTO.getNumber(), authDTO.getPassword()));
        CustomUserDetails user = (CustomUserDetails) authenticate.getPrincipal();

        ProfileEntity profile = user.getProfile();
        ProfileDTO dto = new ProfileDTO();
        dto.setJwt(JwtUtil.encode(profile.getId()));

        return dto;
    }

    public String verification(VerificationDTO dto) {
        Optional<SmsHistoryEntity> optional = smsHistoryRepository.findTopByPhoneOrderByCreatedDateDesc(dto.getPhone());
        if (optional.isEmpty()) {
            return "Phone Not Found";
        }

        SmsHistoryEntity sms = optional.get();
        LocalDateTime validDate = sms.getCreatedDate().plusMinutes(1);

        if (!sms.getCode().equals(dto.getCode())) {
            return "Code Invalid";
        }
        if (validDate.isBefore(LocalDateTime.now())) {
            return "Time is out";
        }

        profileRepository.updateStatusByPhone(dto.getPhone(), ProfileStatus.ACTIVE);
        return "Verification Done";
    }

    public ResponseInfoDTO resendSms(String phone) {
        Long count = smsService.getSmsCount(phone);
        if (count >= 4) {
            return new ResponseInfoDTO(-1, "Limit dan o'tib ketgan");
        }
        smsService.sendRegistrationSms(phone);
        return new ResponseInfoDTO(1);
    }
}
