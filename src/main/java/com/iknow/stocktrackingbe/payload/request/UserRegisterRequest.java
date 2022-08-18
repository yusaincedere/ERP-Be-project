package com.iknow.stocktrackingbe.payload.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    @Email
    @JsonProperty("user_name")
    private String username;
    @NotEmpty
    @Size(min = 8,max = 15)
    private String password;
    private String name;
    @JsonProperty("last_name")
    private String lastName;

}
