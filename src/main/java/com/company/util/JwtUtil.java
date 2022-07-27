package com.company.util;

import com.company.dto.JwtDTO;
import com.company.enums.ProfileRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String secretKey = "Sekratniy_kalit";

//    public static String encode(Integer id) {
//        JwtBuilder jwtBuilder = Jwts.builder();
//        jwtBuilder.setIssuedAt(new Date());
//        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)));
//        jwtBuilder.setIssuer("Mazgi production");
//        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
//        jwtBuilder.claim("id", id);
//
//        String jwt = jwtBuilder.compact();
//        return jwt;
//    }

//    public static JwtDTO decode(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//        JwtDTO jwtDTO = new JwtDTO();
//        jwtDTO.setId((Integer) claims.get("id"));
//        jwtDTO.setRole(claims.get("role").toString());
//        return jwtDTO;
//    }

    public static String encode(Integer id) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date()); // 18:58:00
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000))); // 19:58:00
        jwtBuilder.setIssuer("Youtube project");
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.claim("id", id);

        return jwtBuilder.compact();
    }

    public static Integer decode(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return (Integer) claims.get("id");
    }
}
