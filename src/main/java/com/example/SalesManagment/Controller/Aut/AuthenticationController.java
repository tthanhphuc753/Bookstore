package com.example.SalesManagment.Controller.Aut;

import com.example.SalesManagment.Controller.userController.UserServices;
import com.example.SalesManagment.DAO.UserRepository;
import com.example.SalesManagment.DAO.VerificationTokenRepository;
import com.example.SalesManagment.Model.User.User;
import com.example.SalesManagment.Model.token.VerificationToken;
import com.example.SalesManagment.event.RegistrationCompleteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserServices userServices;
    private final UserRepository userrepos;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userServices.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "redirect:/api/auth/registration-form?success";
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
        String verificationResult = userServices.validateToken(token);
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
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request) {
       return ResponseEntity.ok(userServices.authenticate(request));
    }

    @GetMapping("/homepage")
    public String showHomePage() {
        return "homePage";
    }

    @PostMapping("/api/auth/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("JWT_TOKEN", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);


        return "redirect:/api/auth/login";
    }


    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

    }





}
