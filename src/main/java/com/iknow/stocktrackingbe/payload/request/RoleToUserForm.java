package com.iknow.stocktrackingbe.payload.request;

import lombok.Data;

@Data
public class RoleToUserForm {
    private String username;
    private String roleName;
}
