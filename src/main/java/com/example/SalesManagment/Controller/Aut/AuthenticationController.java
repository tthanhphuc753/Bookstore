package com.example.SalesManagment.Controller.Aut;

import com.example.SalesManagment.DAO.VerificationTokenRepository;
import com.example.SalesManagment.Model.User.User;
import com.example.SalesManagment.Model.token.VerificationToken;
import com.example.SalesManagment.Controller.userController.UserServices;
import com.example.SalesManagment.event.RegistrationCompleteEvent;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserServices userServices;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest,
                               final HttpServletRequest request) {
        User user = userServices.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success! please, check your email to complete your registration";
    }


    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()) {
            return "This account already been verify, please login";
        }
        String verificationResult = userServices.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")) {
            return "Email verified successfull. Now you can login ";
        }
        return "invalid verification Token";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    )
    {
        return ResponseEntity.ok(userServices.authenticate(request));
    }



    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

    }


}
