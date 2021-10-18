package com.demo.neo.soft.springboot.spring.security.jwt.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDetails {
	
	private String userId;
	
    private String userName;
    
    private String roles;
}
