package com.demo.neo.soft.springboot.spring.security.jwt.app.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.demo.neo.soft.springboot.spring.security.jwt.app.model.ValidateResponse;
import com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl.UserDetailsImpl;
import com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl.UsernamePasswordAuthenticationTokenImpl;
import com.demo.neo.soft.springboot.spring.security.jwt.app.util.ConfigUtils;

import lombok.SneakyThrows;

public class JwtAuthProvider extends AbstractUserDetailsAuthenticationProvider{

	    @Autowired
	    private ConfigUtils configUtils;

	    @Override
	    @SneakyThrows
	    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
	        // NO-OP required
	    }

	    @Override
	    @SneakyThrows
	    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken authToken) {
	        try {
	            UsernamePasswordAuthenticationTokenImpl token = (UsernamePasswordAuthenticationTokenImpl) authToken;

	            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	            headers.add("x-auth-token", token.getToken());
	            headers.add(HttpHeaders.AUTHORIZATION, configUtils.getBasicAuth());

	            HttpEntity<UsernamePasswordAuthenticationTokenImpl> entity = new HttpEntity<>(headers);
	            ValidateResponse resData = new RestTemplate().exchange(configUtils.getValidateUrl(), HttpMethod.GET, entity,
	                    ValidateResponse.class).getBody();

	            List<GrantedAuthority> grantedAuthorities =
	                    AuthorityUtils.commaSeparatedStringToAuthorityList(resData.getRoles());

	            return new UserDetailsImpl();
	        } catch (Exception ex) {
	            throw new RuntimeException("Invalid AccessToken");
	        }
	    }

	    @Override
	    public boolean supports(Class<?> authentication) {
	        return UsernamePasswordAuthenticationTokenImpl.class.isAssignableFrom(authentication);
	    }
	}
