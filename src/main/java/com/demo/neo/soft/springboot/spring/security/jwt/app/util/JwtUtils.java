package com.demo.neo.soft.springboot.spring.security.jwt.app.util;

import org.springframework.stereotype.Component;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Student;
import com.demo.neo.soft.springboot.spring.security.jwt.app.model.AuthToken;
import com.demo.neo.soft.springboot.spring.security.jwt.app.model.JwtUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Value;

import java.util.Date;



@Component
public class JwtUtils {
	
	private String SECRET_KEY = "secret";
	
    public AuthToken generateToken(Student student) {
        Claims claims = Jwts.claims().setSubject(student.getStudentId());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setExpiration(new Date(System.currentTimeMillis() + (300 * 1000)));
        claims.put("userName", student.getFirstName());
        claims.put("role", "customer");
        return AuthToken
                .builder()
                .token(Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .compact())
                .build();
    }

    public JwtUserDetails validateToken(String authToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken).getBody();
            Date expireTime = claims.getExpiration();

            if (expireTime.before(new Date(System.currentTimeMillis()))) {
                throw new RuntimeException("token is expired");
            }

            return JwtUserDetails
                    .builder()
                    .userName((String) claims.get("userName"))
                    .roles((String) claims.get("role"))
                    .userId(claims.getSubject())
                    .build();

        } catch (Exception ex) {
            throw new RuntimeException("token is not valid");
        }
    }
}