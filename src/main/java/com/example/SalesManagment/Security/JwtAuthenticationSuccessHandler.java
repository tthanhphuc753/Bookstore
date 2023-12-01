package com.example.SalesManagment.Security;

import com.example.SalesManagment.DAO.UserRepository;
import com.example.SalesManagment.Model.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userrepos;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        String email = authentication.getName();

        Optional<User> userOptional = userrepos.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            var userAuthDetails = new UserAuthDetails(user);
            String role = user.getRole();
            String name = user.getLastName();
            String username = user.getEmail();
            String token = jwtService.generateToken(userAuthDetails);

            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setMaxAge(3600);
            cookie.setPath("/");

            response.addCookie(cookie);
            getRedirectStrategy().sendRedirect(request, response, "/api/auth/homepage");

            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            throw new RuntimeException("User not found: " + email);
        }
    }
}


