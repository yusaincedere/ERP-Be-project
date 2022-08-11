package com.iknow.stocktrackingbe.model;

import com.iknow.stocktrackingbe.idGenerator.idGenerator;
import com.iknow.stocktrackingbe.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@Entity
@Table(name = "users",uniqueConstraints = { @UniqueConstraint(name = "unique_userName", columnNames = { "username"}) })
public class User extends idGenerator {

    private String name;
    private String lastName;
    @Email
    @Column(name = "username",unique = true)
    private String username;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;



}
