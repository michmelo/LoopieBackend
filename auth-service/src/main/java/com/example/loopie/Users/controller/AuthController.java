package com.example.loopie.Users.controller;

import com.example.loopie.Users.dto.AuthenticationRequest;
import com.example.loopie.Users.dto.AuthenticationResponse;
import com.example.loopie.Users.model.User;
import com.example.loopie.Users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        User savedUser = userService.createUser(request);
        return ResponseEntity.ok(new AuthenticationResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails user = userService.loadUserByUsername(request.getUsername());
        User userEntity = (User) user;
        return ResponseEntity.ok(new AuthenticationResponse(userEntity));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            User user = (User) userService.loadUserByUsername(userDetails.getUsername());
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }
}
