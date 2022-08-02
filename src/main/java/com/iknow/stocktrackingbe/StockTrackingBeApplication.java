package com.iknow.stocktrackingbe;

import com.iknow.stocktrackingbe.model.Role;
import com.iknow.stocktrackingbe.model.User;
import com.iknow.stocktrackingbe.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StockTrackingBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTrackingBeApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService service){
		return args -> {
			//service.saveUser(User.builder().name("yusa").lastName("incedere").role(Role.ROLE_USER).password("1234").username("yusa@gmail.com").build());
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
