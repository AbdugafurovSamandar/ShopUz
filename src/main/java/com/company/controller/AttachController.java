package com.company.controller;

import com.company.dto.AttachDTO;
import com.company.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    @Lazy
    private AttachService attachService;

    @PostMapping("/create")
    public ResponseEntity<AttachDTO> create(@RequestParam("file") MultipartFile file) {
        AttachDTO filename = attachService.create(file);
        return ResponseEntity.ok().body(filename);
    }

    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }
}