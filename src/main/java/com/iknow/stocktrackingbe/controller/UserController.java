package com.iknow.stocktrackingbe.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.iknow.stocktrackingbe.helper.JsonHelper;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.payload.request.RoleToUserForm;
import com.iknow.stocktrackingbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JsonHelper jsonHelper;

    @GetMapping(path = "/users")
    public ResponseEntity<JsonNode> getUsers(Pageable page){
        try {
            return new ResponseEntity<>(jsonHelper.objectJson(userService.getUsers(page)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }

    }
    @GetMapping(path = "/username/{username}")
    public ResponseEntity<JsonNode> getUser(@PathVariable String username){
        try {
            return new ResponseEntity<>(jsonHelper.objectJson(userService.getUserByUserName(username)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }

    }
    @PostMapping(path = "/saveUser")
    public  ResponseEntity<JsonNode> saveUser(@RequestBody User user){

        try {
            userService.saveUser(user);
            return new ResponseEntity<>(jsonHelper.messageJson("User created successfully."), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }

    }
    @PostMapping("/updateUser/{username}")
    public ResponseEntity<JsonNode> updateUser(@PathVariable String username, @RequestBody User user) {
        try {
            userService.updateUser(username, user);
            return new ResponseEntity<>(jsonHelper.messageJson("User updated successfully."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping(path = "/role/addtouser")
    public  ResponseEntity<JsonNode> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        try {
            userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
            return new ResponseEntity<>(jsonHelper.messageJson("Role added to user successfully."), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(jsonHelper.messageJson(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }

    }

}
