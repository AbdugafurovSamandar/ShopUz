package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.ChangePasswordDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.ProfileUpdateDTO;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.exp.ProfileAlreadyExists;
import com.company.repository.ProfileRepository;
import com.company.util.MD5Util;
import com.company.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public void create(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByPhone(dto.getPhone());
        if (optional.isPresent()) {
            throw new ProfileAlreadyExists("User already exists");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5Util.getMd5(dto.getPassword()));
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        entity.setRole(dto.getRole());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());

        profileRepository.save(entity);
    }

    public PageImpl pagination(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProfileEntity> all = profileRepository.findAll(pageable);

        List<ProfileEntity> list = all.getContent();

        List<ProfileDTO> dtoList = new LinkedList<>();

        list.forEach(entity -> {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setPhone(entity.getPhone());
            dto.setRole(entity.getRole());
            dtoList.add(dto);
        });

        return new PageImpl(dtoList, pageable, all.getTotalElements());
    }

    public String update(ProfileUpdateDTO dto) {
        ProfileEntity currentUser = getCurrentUser();

        ProfileEntity profile = get(currentUser.getId());
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setBirthDate(dto.getBirthDate());
        profileRepository.save(profile);

        return "Muvaffaqiyatli o‘zgartirildi";
    }

    public void changePassword(ChangePasswordDTO dto) {
        ProfileEntity currentUser = getCurrentUser();
        ProfileEntity profile = get(currentUser.getId());
        if (!profile.getPassword().equals(MD5Util.getMd5(dto.getOldPassword()))) {
            throw new ProfileAlreadyExists("Eski parol xato kiritildai");
        }
        profile.setPassword(MD5Util.getMd5(dto.getNewPassword()));
        profileRepository.save(profile);

    }

    public String delete(Integer id) {
        ProfileEntity entity = get(id);
        entity.setVisible(Boolean.FALSE);
        profileRepository.save(entity);
        return "Muvaffaqiyatli o‘chirildi";
    }

    public String changeStatus(Integer id) {
        ProfileEntity entity = get(id);
        if (entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            entity.setStatus(ProfileStatus.BLOCK);
        } else {
            entity.setStatus(ProfileStatus.ACTIVE);
        }
        profileRepository.save(entity);

        return "Status muvaffaqiyatli o'zgartirildi";
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ProfileAlreadyExists("Foydalanuvchi topilmadi!");
        });
    }


    public ProfileEntity getCurrentUser() {
        return SecurityUtil.getCurrentUser().getProfile();
    }

}