package com.umc.Palette;

import com.umc.Palette.domain.user.Role;
import com.umc.Palette.domain.user.domain.User;
import com.umc.Palette.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class PaletteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaletteApplication.class, args);
	}


}
