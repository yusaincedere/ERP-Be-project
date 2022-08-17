package com.iknow.stocktrackingbe.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iknow.stocktrackingbe.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class JwtHelper {
    @Value("${jwt-variables.KEY}")
    private String KEY;


    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;


    public String createJwtToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());

        String role = user.getRole().name();

        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .withIssuedAt(convertToDateViaSqlDate(LocalDate.now()))
                .withClaim("role", role).sign(algorithm);
    }
    public String generateRefreshToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public UsernamePasswordAuthenticationToken getAuthToken(String token) {

        DecodedJWT decodedJWT = verifyJWT(token);
        String username = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("role").asString();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);

    }


    public DecodedJWT verifyJWT(String token) {
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(EXPIRES_ACCESS_TOKEN_MINUTE * 60).build();

        try {
            return verifier.verify(token);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid token");
        }
    }
    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }
}
