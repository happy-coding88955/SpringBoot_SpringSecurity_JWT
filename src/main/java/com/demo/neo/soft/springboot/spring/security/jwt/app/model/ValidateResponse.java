package com.demo.neo.soft.springboot.spring.security.jwt.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateResponse {

    private String userId;
    private String userName;
    private String roles;
}