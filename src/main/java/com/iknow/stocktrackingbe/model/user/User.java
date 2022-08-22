package com.iknow.stocktrackingbe.model.user;

import com.iknow.stocktrackingbe.model.BaseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(name = "unique_userName", columnNames = { "username"}) })
public class User extends BaseEntity {

    private String name;
    private String lastName;
    @Email
    @Column(name = "username",unique = true)
    private String username;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    private LocalDate lastUpdated;



}
