package com.company.controller;

import com.company.exp.BadRequestException;
import com.company.exp.ProfileAlreadyExists;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({ProfileAlreadyExists.class, BadRequestException.class})
    public ResponseEntity<String> handler(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
