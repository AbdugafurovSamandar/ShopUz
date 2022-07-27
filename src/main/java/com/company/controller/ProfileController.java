package com.company.controller;

import com.company.dto.ChangePasswordDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.ProfileUpdateDTO;
import com.company.dto.ResponseInfoDTO;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/update_detail")
    public ResponseEntity<String> updateDetail(@RequestBody ProfileUpdateDTO dto) {
       profileService.update(dto);
        return ResponseEntity.ok().body("Muvaffaqiyatli o‘zgartirildi");
    }

    @PostMapping("/change_pass")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO dto){

         profileService.changePassword(dto);
        return ResponseEntity.ok().body("Muvaffaqiyatli o‘zgartirildi");

    }

}
