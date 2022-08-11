package com.iknow.stocktrackingbe.payload.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    @NotEmpty
    @Email
    private String username;
    @NotEmpty
    @Size(min = 8,max = 15)
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    @JsonProperty("last_name")
    private String lastName;
}
