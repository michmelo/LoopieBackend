package com.example.loopie.Users.controller;

import com.example.loopie.Users.dto.AuthenticationRequest;
import com.example.loopie.Users.dto.AuthenticationResponse;
import com.example.loopie.Users.model.User;
import com.example.loopie.Users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                );

        authenticationManager.authenticate(authToken);

        User userEntity = (User) userService.loadUserByUsername(request.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(userEntity));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            User user = (User) userService.loadUserByUsername(userDetails.getUsername());
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(401).build();
    }
}
