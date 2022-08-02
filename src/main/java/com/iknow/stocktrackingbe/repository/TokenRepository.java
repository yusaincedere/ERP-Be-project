package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TokenRepository  extends JpaRepository<Token,String> {
    Optional<Token> findByToken(String token);

}
