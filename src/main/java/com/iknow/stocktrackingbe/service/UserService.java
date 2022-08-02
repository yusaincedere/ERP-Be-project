package com.iknow.stocktrackingbe.service;

import com.iknow.stocktrackingbe.exception.NotFoundException;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.model.Role;
import com.iknow.stocktrackingbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final TokenService tokenService;
     private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void saveUser(User user){
        logger.info("Service Called: createAppUser");
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already exists!");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            tokenService.saveConfirmationToken(userRepository.save(user), user.getPassword());
        }
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
}
