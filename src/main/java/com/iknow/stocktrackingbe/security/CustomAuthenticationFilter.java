package com.iknow.stocktrackingbe.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iknow.stocktrackingbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


    @Override
    public Authentication  attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {

        org.springframework.security.core.userdetails.User user = (User) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        LocalDateTime localDateTime = LocalDateTime.now().plusYears(100);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        String access_token = JWT.create().withSubject(user.getUsername()).withExpiresAt(date)
                .withClaim("role",
                        user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(request.getRequestURL().toString()).sign(algorithm);

        com.iknow.stocktrackingbe.model.User u = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found."));

        Map<String, String> userResponse = new HashMap<>();
        userResponse.put("username", u.getUsername());
        userResponse.put("name", u.getName());
        userResponse.put("role", u.getRole().name());
        userResponse.put("access_token", access_token);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), userResponse);
    }
}
