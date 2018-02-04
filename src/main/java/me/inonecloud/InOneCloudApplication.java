package me.inonecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InOneCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(InOneCloudApplication.class, args);
	}

	/*@Autowired
	public void authenticationManager(AuthenticationManagerBuilder bilder, UserRepository repo) throws Exception{
		bilder.userDetailsService(s -> new CustomDetailUser(repo.findByUsername(s)));
	}*/
}
