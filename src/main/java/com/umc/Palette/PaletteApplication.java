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
public class PaletteApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaletteApplication.class, args);
	}


	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ROLE_ADMIN);
		if (null == adminAccount) {
			User user = new User();

			user.setEmail("admin@gmail.com");
			user.setName("admin");
			user.setNickname("admin");
			user.setLoginId("ad");
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setIntroduction("I am admin");
			userRepository.save(user);
		}
	}

}
