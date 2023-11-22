package com.farm.management.controller;

import com.farm.management.exception.AppException;
import com.farm.management.model.User;
import com.farm.management.model.UserLevel;
import com.farm.management.payload.ApiResponse;
import com.farm.management.payload.ChangePasswordRequest;
import com.farm.management.payload.JwtAuthenticationResponse;
import com.farm.management.payload.LoginRequest;
import com.farm.management.payload.SignUpRequest;
import com.farm.management.repository.UserLevelRepository;
import com.farm.management.repository.UserRepository;
import com.farm.management.security.CurrentUser;
import com.farm.management.security.JwtTokenProvider;
import com.farm.management.security.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLevelRepository userLevelRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<Object>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        
        System.out.println(signUpRequest.getEmail() + ' ' + signUpRequest.getName() + ' ' + signUpRequest.getPassword());

        // Creating user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserLevel userRole = userLevelRepository.findByDescription("ADMIN")
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setUserLevel(userRole);
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
    
    @PostMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> changePaassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest, @CurrentUser UserPrincipal currentUser) {
    	String oldPassword = changePasswordRequest.getOldPassword();
    	String newPassword = changePasswordRequest.getNewPassword();
    	if(passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
    		userRepository.changePassword(currentUser.getId(), passwordEncoder.encode(newPassword));
    		return new ResponseEntity<>("Password successfully changed!",HttpStatus.OK);    			
    	} else return new ResponseEntity<>("Password not matched!", HttpStatus.BAD_REQUEST); 
    }
}
