package com.demo.neo.soft.springboot.spring.security.jwt.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import com.demo.neo.soft.springboot.spring.security.jwt.app.service.impl.UsernamePasswordAuthenticationTokenImpl;

public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

	protected JwtAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		try {
			String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);

			if (StringUtils.isEmpty(authToken)) {
				throw new RuntimeException("No Jwt token found in header");
			}

			UsernamePasswordAuthenticationTokenImpl token = new UsernamePasswordAuthenticationTokenImpl(authToken);
			return getAuthenticationManager().authenticate(token);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
			return null;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}
