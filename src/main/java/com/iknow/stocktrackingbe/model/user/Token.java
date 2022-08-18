package com.iknow.stocktrackingbe.model.user;

import com.iknow.stocktrackingbe.BaseEntity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "confirmation_tokens")

public class Token extends BaseEntity {

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
