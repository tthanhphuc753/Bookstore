package com.example.Bookstore.Domain.AuthService;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Model.User.UserAuthDetails;
import com.example.Bookstore.Domain.Model.token.VerificationToken;
import com.example.Bookstore.Domain.Security.JWTAuth.JwtService;
import com.example.Bookstore.Persistence.DAO.UserRepository;
import com.example.Bookstore.Persistence.DAO.VerificationTokenRepository;
import com.example.Bookstore.Presentation.Controller.Auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class AuthenServiceImp implements AuthenService {
    private final UserRepository userrepos;
    private final VerificationTokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthenServiceImp.class);

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = userrepos.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng cho email: " + request.email()));

        var user1 = new UserAuthDetails(user);
        logger.info("JWT: " + jwtService.generateToken(user1));

        String role = user.getRole();
        String name = user.getLastName();
        String username = user.getEmail();
        String jwtToken = jwtService.generateToken(user1);
        logger.info("Role: " + user1.getAuthorities());
        System.out.println("ROLE: " + user1.getAuthorities());
        return AuthenticationResponse.builder().username(username).role(role).name(name).token(jwtToken).build();
    }

    public String validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if (token == null) {
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(token);
            return "Token already expired";
        }
        user.setEnabled(true);
        userrepos.save(user);
        return "Valid";
    }

    @Override
    public VerificationToken getToken(String token)
    {
       return verificationTokenRepository.findByToken(token);
    }
}
