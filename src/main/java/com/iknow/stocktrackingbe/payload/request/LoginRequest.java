package com.iknow.stocktrackingbe.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRequest {

    @Email
    @NotNull
    private String username;
    @Size(min = 8,max = 15)
    private String password;

}
