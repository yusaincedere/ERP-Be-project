package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository  extends JpaRepository<RefreshToken,String> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUsername(String username);

    void deleteRefreshTokenByUsername(String username);
}
