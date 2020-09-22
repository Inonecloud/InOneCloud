package me.inonecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InonecloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(InonecloudApplication.class, args);
    }
}
