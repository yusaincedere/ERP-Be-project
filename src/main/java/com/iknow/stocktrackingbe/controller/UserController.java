package com.iknow.stocktrackingbe.controller;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.payload.request.RoleToUserForm;
import com.iknow.stocktrackingbe.payload.request.UserRegisterRequest;
import com.iknow.stocktrackingbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping(path = "/users")
    public Page<User> getUsers(Pageable page){
            return userService.getUsers(page);
    }
    @GetMapping(path = "/username/{username}")
    public User getUser(@PathVariable String username){
            return userService.getUserByUserName(username);
    }

    @PostMapping("/updateUser/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User user) {
            userService.updateUser(username, user);
    }

    @PutMapping(path = "/role/addToUser")
    public  void addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
    }
}
