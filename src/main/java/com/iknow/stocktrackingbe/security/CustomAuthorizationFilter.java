package com.iknow.stocktrackingbe.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.repository.UserRepository;
import com.iknow.stocktrackingbe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final AuthService authService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/login")||request.getServletPath().equals("/auth/register")){
            filterChain.doFilter(request,response);
        }else{
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String token = authorizationHeader.substring("Bearer ".length());
                    UsernamePasswordAuthenticationToken authToken;
                    System.out.println(token);
                    authToken = authService.getAuthToken(token);
                    System.out.println(authToken.getName());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    filterChain.doFilter(request, response);

                }catch (Exception e){
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid authentication");
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }
    }
}
