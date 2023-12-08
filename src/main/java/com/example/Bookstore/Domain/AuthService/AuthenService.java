package com.example.Bookstore.Domain.AuthService;

import com.example.Bookstore.Domain.Security.AuthenticationRequest;
import com.example.Bookstore.Presentation.Controller.Auth.AuthenticationResponse;

public interface AuthenService {

    AuthenticationResponse authenticate(AuthenticationRequest request);
    String validateToken(String theToken);
}
