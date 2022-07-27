package com.company.config;


import com.company.entity.ProfileEntity;
import com.company.enums.ProfileGender;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.repository.ProfileRepository;
import com.company.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class InitConfig {

    @Autowired
    private ProfileRepository profileRepository;

    @Bean
    CommandLineRunner ok() {
        return args -> {
            Optional<ProfileEntity> optional = profileRepository.findByPhone("+998907880223");
            if (optional.isPresent()) {
                return;
            }
            String md5 = MD5Util.getMd5("123");

            ProfileEntity admin = new ProfileEntity();
            admin.setName("Admin");
            admin.setSurname("adminjon");
            admin.setPhone("+998907880223");
            admin.setPassword(md5);
            admin.setGender(ProfileGender.MALE);
            admin.setBirthDate(LocalDate.parse("2000-05-13"));
            admin.setVisible(true);
            admin.setStatus(ProfileStatus.ACTIVE);
            admin.setRole(ProfileRole.ROLE_ADMIN);
            profileRepository.save(admin);

        };

    }
}
