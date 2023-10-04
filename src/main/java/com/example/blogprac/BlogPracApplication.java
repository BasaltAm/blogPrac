package com.example.blogprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlogPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogPracApplication.class, args);
    }

}
