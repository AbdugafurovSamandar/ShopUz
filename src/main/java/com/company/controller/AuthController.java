package com.company.controller;

import com.company.dto.*;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegistrationDTO dto) {
        authService.registration(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO dto) {
        ProfileDTO login = authService.login(dto);
        return ResponseEntity.ok().body(login);
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verification(@RequestBody VerificationDTO dto) {
        String response = authService.verification(dto);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/resendsms/{phone}")
    public ResponseEntity<ResponseInfoDTO> resendSms(@PathVariable("phone") String phone) {
        ResponseInfoDTO response = authService.resendSms(phone);
        return ResponseEntity.ok(response);
    }
}
