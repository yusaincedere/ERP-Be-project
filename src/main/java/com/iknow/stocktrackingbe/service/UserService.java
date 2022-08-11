package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.model.Role;
import com.iknow.stocktrackingbe.payload.request.UserRegisterRequest;
import com.iknow.stocktrackingbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
     private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public User saveUser(UserRegisterRequest userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists");
        }
        User user = new User().toBuilder()
                .name(userRequest.getName())
                .lastName(userRequest.getLastName())
                .password(userRequest.getPassword())
                .username(userRequest.getUsername()).build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }


    public void addRoleToUser(String username,String roleName){
        logger.info("Service Called: addRoleToUser");
         User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found."));
         if(roleName == "ROLE_USER"){
             user.setRole(Role.ROLE_USER);
         }
        else if(roleName == "ROLE_ADMIN"){
            user.setRole(Role.ROLE_ADMIN);
        }
    }

    public User getUserByUserName(String username){

       Optional<User> user = userRepository.findByUsername(username);
       if(user.isPresent()){
           return user.get();
       }else{
           throw new NotFoundException("User not found.");
       }
    }

    public Page<User> getUsers(Pageable page){
        logger.info("Service Called: getUsers");
        Page<User> users = userRepository.findAll(page);
        if(!users.isEmpty()){
            return users;
        }else {
            throw new NotFoundException("There is no User");
        }
    }

    public void updateUser(String username, User user) {
        Optional<User> existingUser =userRepository.findByUsername(username);

        if(existingUser.isPresent()){
            User userObject = existingUser.get();
            userObject.setName(user.getName() == null ? existingUser.get().getName() : user.getName());
            userObject.setUsername(user.getUsername() == null ? existingUser.get().getUsername() : user.getUsername());
            userObject.setRole(user.getRole() == null  ? existingUser.get().getRole() : user.getRole());
            userObject.setLastName(user.getLastName() == null  ? existingUser.get().getLastName() : user.getLastName());
            userRepository.save(userObject);
        }else {
            throw new NotFoundException("User not found!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("User not found!"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, Collections.singletonList(authority));
    }

    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteUser(String username) {
        User existingUser = getUserByUserName(username);
        userRepository.delete(existingUser);
    }

    public User grantRole(String username, String roleName) {
        User user = getUserByUserName(username);
        if (roleName.equals(Role.ROLE_USER.toString())) {
           user.setRole(Role.ROLE_USER);
        } else if (roleName.equals(Role.ROLE_ADMIN.toString())) {
            user.setRole(Role.ROLE_ADMIN);
        } else {
            throw new IllegalStateException("Invalid role name");
        }
        return user;
    }

    private boolean isAuthorized(User unknownUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByUserName(auth.getName());

        var isAdmin = doesIncludesRoles(List.of(Role.ROLE_USER, Role.ROLE_ADMIN), user.getRole());

        var isOwner = unknownUser.getId().equals(user.getId());

        return isAdmin || isOwner;
    }

    public User getMyself() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUserByUserName(auth.getName());
    }

    protected boolean doesIncludesRoles(List<Role> checkRoles, Role userRole) {
       for(Role role:checkRoles){
           if(role.equals(userRole)){
               return true;
           }
       }
       return false;
    }


    public User getUserByPermit(String username) {
        User user = getUserByUserName(username);
        if (!isAuthorized(user)) {
            throw new IllegalStateException("You can not make this operation!");
        }
        return user;
    }

    public User checkLoginUser(String username, String password) {
        User user = getUserByUserName(username);
        logger.info("Login attempt user: " + user.toString());
        logger.info("login attempt user password: " + user.getPassword() + "   request password: " + password);
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new IllegalStateException("Wrong password!");
        }

        return user;
    }
}
