package com.example.SalesManagment;

import com.example.SalesManagment.DAO.UserRepository;
import com.example.SalesManagment.Model.User.User;
import com.example.SalesManagment.Security.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SalesManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesManagmentApplication.class, args);
    }

    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User Phuc = new User(null, "Phuc", "Tran", 10, null
                    , "tthanhphuc753@gmail.com"
                    , applicationConfig.passwordEncoder().encode("123456")
                    , "ADMIN", null, true, null);
            userRepository.save(Phuc);

            User Sor = new User(null, "Sor", "Q", 10, null
                    , "tthanhphuc752@gmail.com"
                    , applicationConfig.passwordEncoder().encode("123456")
                    , "USER", null, true, null);
            userRepository.save(Sor);
        };

    }
}
