package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.helper.JwtHelper;
import com.iknow.stocktrackingbe.model.RefreshToken;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.payload.request.LoginRequest;
import com.iknow.stocktrackingbe.payload.request.UserRegisterRequest;
import com.iknow.stocktrackingbe.repository.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class AuthService {

    @Value("${jwt-variables.EXPIRES_REFRESH_TOKEN_MINUTE}")
    private String EXPIRES_REFRESH_TOKEN_MINUTE;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtHelper jwtHelper;

    public AuthService(UserService userService, RefreshTokenRepository refreshTokenRepository, JwtHelper jwtHelper) {

        this.userService = userService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtHelper = jwtHelper;
    }

    private RefreshToken createRefreshToken(User user) {
        logger.info("Service called: createRefreshToken");
        RefreshToken refreshToken = new RefreshToken();
        String token;
        do {
            token = jwtHelper.generateRefreshToken();

        } while (refreshTokenRepository.findByToken(token).isPresent());

        refreshToken.setUsername(user.getUsername());
        refreshToken.setToken(token);
        refreshToken.setExpiryDate(LocalDateTime.now().plusMinutes(Long.parseLong(EXPIRES_REFRESH_TOKEN_MINUTE)));
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    public Map<String, String> createTokens(User user) {
        return createTokens(user.getUsername());
    }

    public Map<String, String> createTokens(String username) {

        User user = userService.getUserByUserName(username);

        String access_token = jwtHelper.createJwtToken(user);
        logger.info("Token created");
        RefreshToken refreshToken = createRefreshToken(user);

        Map<String, String> map = new HashMap();
        map.put("access_token", access_token);
        map.put("refresh_token", refreshToken.getToken());
        map.put("username", user.getUsername());
        map.put("id", user.getId());
        return map;
    }

    public Map<String, String> registerUser(UserRegisterRequest userRegisterRequest) {

        User user = userService.saveUser(userRegisterRequest);
        return createTokens(user.getUsername());
    }

    public Map<String, String> login(LoginRequest loginRequest) {
        deleteOldTokensByLogin(loginRequest.getUsername());
        User user = userService.getUserByUserName(loginRequest.getUsername());
        logger.info("Map Login password: "+loginRequest.getPassword());
        userService.checkLoginUser(loginRequest.getUsername(), loginRequest.getPassword());
        logger.info("Login successful");
        return createTokens(user);
    }

    public void logout() {
        deleteOldTokensByUsername(getAuthenticatedUser().getUsername());
    }


    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserName(auth.getName());
    }
    public UsernamePasswordAuthenticationToken getAuthToken(String token) {
        return jwtHelper.getAuthToken(token);
    }

    public Map<String, String> refreshTokens(String refreshToken) {

        RefreshToken token = getRefreshToken(refreshToken);

        if (token != null) {
            deleteRefreshToken(token.getToken());

            return createTokens(token.getUsername());
        } else
            throw new IllegalStateException("Invalid refresh token!");

    }

    public void deleteRefreshToken(String token) {
        RefreshToken refreshToken = getRefreshToken(token);
        refreshTokenRepository.delete(refreshToken);
    }

    public void deleteOldTokensByLogin(String username) {
        refreshTokenRepository.deleteRefreshTokenByUsername(username);
    }
    public void deleteOldTokensByUsername(String username) {

        if (refreshTokenRepository.findByUsername(username).isPresent()) {
            refreshTokenRepository.deleteRefreshTokenByUsername(username);
        } else {
            throw new IllegalStateException("You have already been loguted!");
        }

    }

    protected RefreshToken getRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));
    }

}
