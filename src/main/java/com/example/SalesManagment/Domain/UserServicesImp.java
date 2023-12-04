package com.example.SalesManagment.Domain;

import com.example.SalesManagment.Presentation.Controller.Auth.AuthenticationRequest;
import com.example.SalesManagment.Presentation.Controller.Auth.AuthenticationResponse;
import com.example.SalesManagment.Presentation.Controller.Auth.RegistrationRequest;
import com.example.SalesManagment.Persistence.DAO.ProductRepository;
import com.example.SalesManagment.Persistence.DAO.UserRepository;
import com.example.SalesManagment.Persistence.DAO.VerificationTokenRepository;
import com.example.SalesManagment.Domain.Exception.Exception;
import com.example.SalesManagment.Domain.Model.Book.Book;
import com.example.SalesManagment.Domain.Model.User.User;
import com.example.SalesManagment.Domain.Model.token.VerificationToken;
import com.example.SalesManagment.Domain.Security.JWTAuth.JwtService;
import com.example.SalesManagment.Domain.Model.User.UserAuthDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor

public class UserServicesImp implements UserServices {

    private final UserRepository userrepos;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(UserServicesImp.class);

    @Override
    public String getUser(Model model) {
        model.addAttribute("user", userrepos.findAll());
        return "user";
    }


    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> existingUser = this.findByEmail(request.email());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with email " + request.email() + " already exists");
        }

        User newUser = new User();
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole("USER"); //

        var user1 = new UserAuthDetails(newUser);
        var jwtToken = jwtService.generateToken(user1);
        logger.info("JWT:" + jwtToken);
        return userrepos.save(newUser);
    }

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

    @Override
    public Optional<User> findByEmail(String email) {
        return userrepos.findByEmail(email);
    }

    public long countUser() {
        if (userrepos.count() <= 0) {
            return 0;
        }
        return userrepos.count();
    }

    public User findUserByID(@RequestParam long id) {
        Optional<User> userOptional = userrepos.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new Exception("User with id: " + id + "does not exist");
        }
    }

    public void addUser(User user) {
        if (isEmailExists(user.getEmail())) {
            throw new IllegalStateException("email taken");
        }
        userrepos.save(user);
    }

    public boolean isEmailExists(String email) {
        return userrepos.existsByEmail(email);
    }

    public void deleteUser(long userID) {
        boolean isExist = userrepos.existsById(userID);
        if (isExist) {
            userrepos.deleteById(userID);
        } else throw new IllegalStateException("User with ID: " + userID + " does not exist");
    }

    @Override
    public User addProduct(Long userID, Long productID) {
        Set<Book> bookList = null;
        User user = userrepos.findById(userID).get();
        Book book = productRepository.findById(productID).get();
        bookList = user.getBookList();
        bookList.add(book);
        user.setBookList(bookList);
        return userrepos.save(user);
    }

    @Override
    public void saveUserVerificationToken(User theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
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

}
