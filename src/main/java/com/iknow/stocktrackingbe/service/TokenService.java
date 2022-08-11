package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.model.Token;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.repository.TokenRepository;
import com.iknow.stocktrackingbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;


    public void saveConfirmationToken(User user, String password) {

        String token = UUID.randomUUID().toString();

        Token confirmationToken = new Token(token,
                LocalDateTime.now(ZoneId.of("Europe/Istanbul")).toString(),
                LocalDateTime.now(ZoneId.of("Europe/Istanbul")).plusDays(30).toString(), user.getId());

        tokenRepository.save(confirmationToken);
    }
}
