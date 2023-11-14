package com.example.SalesManagment;

import com.example.SalesManagment.DAO.UserRepository;
import com.example.SalesManagment.Model.User.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class SalesManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagmentApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner (UserRepository userRepositor) {
		return args ->
		{
			User user1 = new User(1L, "p", 10, LocalDate.now(), "tthanhphuc753@gmail.com", "123456", "USER", true, null);
			userRepositor.save(user1);
		};
	}
}
