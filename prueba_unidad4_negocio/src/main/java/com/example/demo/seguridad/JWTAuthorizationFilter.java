package com.example.demo.seguridad;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String TOKEN_PREFIX_BEARER = "Bearer ";

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String cabeceraToken = request.getHeader(HEADER_AUTHORIZATION);
		if (cabeceraToken==null || !cabeceraToken.startsWith(TOKEN_PREFIX_BEARER))  {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = this.getAuthentication(request, cabeceraToken);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest httpServletRequest, String cabeceraToken) {
		String token = cabeceraToken.replace(TOKEN_PREFIX_BEARER, "");
		String user = Jwts.parser().setSigningKey("semilla01").parseClaimsJws(token).getBody().getSubject();
		if (user != null) {
			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		} else {
			return null;
		}
	}
}
