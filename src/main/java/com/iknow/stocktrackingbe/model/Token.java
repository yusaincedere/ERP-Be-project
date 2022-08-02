package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "confirmation_tokens")

public class Token extends idGenerator {

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "expires_at")
    private String expiresAt;

    @Column(name = "user_id")
    private String userId;



    public Token(String token, String createdAt, String expiresAt, String userId) {
        super();
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.userId = userId;
    }


}
