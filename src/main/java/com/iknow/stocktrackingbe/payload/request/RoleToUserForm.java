package com.iknow.stocktrackingbe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleToUserForm {
    private String username;
    @JsonProperty("role_name")
    private String roleName;
}
