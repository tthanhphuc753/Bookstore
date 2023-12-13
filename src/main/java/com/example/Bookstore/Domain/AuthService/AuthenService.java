package com.example.Bookstore.Domain.AuthService;

import com.example.Bookstore.Domain.Model.token.VerificationToken;
import com.example.Bookstore.Presentation.Controller.Auth.AuthenticationResponse;

public interface AuthenService {

    AuthenticationResponse authenticate(AuthenticationRequest request);

    String validateToken(String theToken);

    VerificationToken getToken(String token);
}
