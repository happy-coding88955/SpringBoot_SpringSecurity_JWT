package com.demo.neo.soft.springboot.spring.security.jwt.app.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {
	
    private String token;
}
