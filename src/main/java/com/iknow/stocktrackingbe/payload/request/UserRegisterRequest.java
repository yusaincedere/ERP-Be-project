package com.iknow.stocktrackingbe.payload.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    @NotEmpty
    @Email
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;
}
