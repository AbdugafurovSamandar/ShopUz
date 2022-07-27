package com.company.controller;


import com.company.dto.ProfileDTO;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create_operator")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> create(@RequestBody ProfileDTO dto) {
        profileService.create(dto);
        return ResponseEntity.ok("Operator muvaffaqqiyatli yaratildi!");
    }

    @GetMapping("/getlist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = profileService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.delete(id));
    }


    @DeleteMapping("/change_status/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> changeStatus(@PathVariable("id") Integer id) {

        return ResponseEntity.ok(profileService.changeStatus(id));
    }
}