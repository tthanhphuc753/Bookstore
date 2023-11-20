package com.example.SalesManagment;

import com.example.SalesManagment.DAO.UserRepository;
import com.example.SalesManagment.Model.User.User;
import com.example.SalesManagment.Security.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SalesManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagmentApplication.class, args);
	}

	@Autowired
	private ApplicationConfig applicationConfig;
	@Bean
	CommandLineRunner commandLineRunner (UserRepository userRepository)
	{
		return  args -> {
			User Phuc = new User(null, "Phuc", 10, null
					,"tthanhphuc753@gmail.com", applicationConfig.passwordEncoder().encode("123456")
					,"USER", null, true,null);
			userRepository.save(Phuc);
		};

	}
}
