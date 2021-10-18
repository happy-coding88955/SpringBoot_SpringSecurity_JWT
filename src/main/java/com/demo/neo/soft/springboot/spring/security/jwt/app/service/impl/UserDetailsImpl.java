package com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.neo.soft.springboot.spring.security.jwt.app.entity.Student;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class UserDetailsImpl  implements UserDetails {

	private Student student;
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("STUDENT"));
	}

	@Override
	public String getPassword() {
		return "PASSWORD11";
	}

	@Override
	public String getUsername() {
		return student.getFirstName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}