package com.company.util;

import com.company.config.CustomUserDetails;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class SecurityUtil {
    public static CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();

    }
}
