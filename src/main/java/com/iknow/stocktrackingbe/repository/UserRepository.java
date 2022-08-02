package com.iknow.stocktrackingbe.repository;

import com.iknow.stocktrackingbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);
}
