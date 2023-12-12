package com.example.Bookstore.Presentation.Controller.Auth;

import com.example.Bookstore.Domain.AuthService.AuthenService;
import com.example.Bookstore.Domain.AuthService.AuthenticationRequest;
import com.example.Bookstore.Domain.AuthService.RegistrationRequest;
import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Model.token.VerificationToken;
import com.example.Bookstore.Domain.UserService.UserServices;
import com.example.Bookstore.Domain.event.RegistrationCompleteEvent;
import com.example.Bookstore.Persistence.DAO.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserServices userServices;
    private final AuthenService authenService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userServices.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "redirect:/auth/registration-form?success";
    }

    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user"
                , new RegistrationRequest("", "", "", ""));
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token, Model model) {
        VerificationToken theToken = tokenRepository.findByToken(token);
        String message = "";
        if (theToken.getUser().isEnabled()) {
            message = "This account already been verify, please login";
            model.addAttribute("mg", "<div>" + message + "</div>");
            return "verifiedEmail";
        }
        String verificationResult = authenService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")) {
            message = "Email verified successfully. Now you can";
            model.addAttribute("mg", "<span>"
                    + message + " <a href=\"/static\" th:href=\"@{/api/auth/login}\">\n"
                    + " login.\n" + "</a></span>");
            return "verifiedEmail";
        }
        message = "invalid verification Token";
        model.addAttribute("mg", "<div>" + message + "</div>");
        return "verifiedEmail";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenService.authenticate(request));
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("JWT_TOKEN", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/auth/login";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

    }


}
