package ru.netology.springboot_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBoot1Application {

    public static void main(String[] args) {

        SpringApplication.run(SpringBoot1Application.class, args);
    }

}
