package com.iknow.stocktrackingbe.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleToUserForm {
    private String username;
    private String roleName;
}
