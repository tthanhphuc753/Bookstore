package com.example.Bookstore.Domain.Security.JWTAuth;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Model.User.UserAuthDetails;
import com.example.Bookstore.Domain.UserService.UserServices;
import com.example.Bookstore.Persistence.DAO.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserServices userServices;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        String email = authentication.getName();

        Optional<User> userOptional = userServices.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            var userAuthDetails = new UserAuthDetails(user);
            String token = jwtService.generateToken(userAuthDetails);

            Cookie cookie = new Cookie("JWT_TOKEN", token);
            cookie.setMaxAge(36000);
            cookie.setPath("/");

            response.addCookie(cookie);
            if (userAuthDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("USER"))) {
                setDefaultTargetUrl("/client/homepage");
            } else {
                setDefaultTargetUrl("/admin/homepage");
            }

            DefaultSavedRequest savedRequest = (DefaultSavedRequest) new HttpSessionRequestCache().getRequest(request, response);
            if (savedRequest != null && savedRequest.getRedirectUrl() != null) {
                setDefaultTargetUrl(savedRequest.getRedirectUrl()); // Sử dụng trang trước đó nếu có
            }

            super.onAuthenticationSuccess(request, response, authentication);
        } else {
            throw new RuntimeException("User not found: " + email);
        }
    }
}


