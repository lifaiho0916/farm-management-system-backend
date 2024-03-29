package com.farm.management.controller;

import com.farm.management.exception.AppException;
import com.farm.management.model.UserLevel;
import com.farm.management.model.User;
import com.farm.management.model.UserFarm;
import com.farm.management.payload.AssignRequest;
import com.farm.management.payload.UserSummary;
import com.farm.management.repository.UserLevelRepository;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.UserPrincipal;
import com.farm.management.service.FarmService;
import com.farm.management.service.UserService;
import com.farm.management.service.UserFarmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FarmService farmService;
    
    @Autowired
    private UserFarmService userFarmService;
    
    @Autowired
    private UserLevelRepository userLevelRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // build create User REST API

    @GetMapping("user/me")
    @PreAuthorize("isAuthenticated()")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getLevel());
    }
    
    @PostMapping("user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> createUser(@RequestBody User user, @CurrentUser UserPrincipal currentUser) {
        user.setUsername(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserLevel userRole = userLevelRepository.findByDescription("USER")
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setUserLevel(userRole);
        user.setCreated_by(currentUser.getId());
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("user/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @GetMapping("users/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<User>> findByCreated_by(@PathVariable("id") Long created_by){
        List<User> users = userService.findByCreated_by(created_by);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("user/{id}")
    @PreAuthorize("isAuthenticated()")
    // http://localhost:8080/api/users/1
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody User user){
        user.setId(userId);
        if(user.getPassword() != null) {
        	user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("user/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
    	userFarmService.deleteUserFarmByUserId(userId);
        userService.deleteUser(userId);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }

    @PostMapping("/user/assign-farm")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createUserfarm(@Valid @RequestBody AssignRequest assignRequest){
    	Long farmId = assignRequest.getFarm_id();
    	Long userId = assignRequest.getUser_id();
    	if(farmId == 0) {
    		userFarmService.deleteUserFarmByUserId(userId);
    	} else {
    		if(userFarmService.isUserIdExist(userId)) {
    			userFarmService.updateUserFarmByUserId(userId, farmId);
    		} else {
    			UserFarm newUserfarm = new UserFarm();
    	        newUserfarm.setFarm(farmService.getFarmById(farmId));
    	        newUserfarm.setUser(userService.getUserById(userId));
    	        userFarmService.createUserfarm(newUserfarm);
    		}
    	}
    	return new ResponseEntity<>("Farm has been allocated successfully", HttpStatus.CREATED);
    }
}
