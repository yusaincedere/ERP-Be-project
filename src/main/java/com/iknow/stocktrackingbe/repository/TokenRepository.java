package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TokenRepository  extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String token);

}
