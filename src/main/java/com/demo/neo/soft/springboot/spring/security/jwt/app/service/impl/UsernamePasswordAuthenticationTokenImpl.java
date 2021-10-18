package com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernamePasswordAuthenticationTokenImpl extends UsernamePasswordAuthenticationToken{

	 private String token;

	    public UsernamePasswordAuthenticationTokenImpl(String token) {
	        super(null, null);
	        this.token = token;

	    }
}
