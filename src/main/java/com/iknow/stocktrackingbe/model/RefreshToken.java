package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "refresh_token")

public class RefreshToken extends BaseEntity {
    private String username;
    @NotEmpty
    @Size(min = 32, max = 32)
    @Column(name = "token", nullable = false, unique = true, length = 32)
    private String token;
    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;


}
