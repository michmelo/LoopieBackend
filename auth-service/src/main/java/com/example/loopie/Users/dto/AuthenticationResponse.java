package com.example.loopie.Users.dto;

import com.example.loopie.Users.model.User;

public class AuthenticationResponse {
    private User user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
