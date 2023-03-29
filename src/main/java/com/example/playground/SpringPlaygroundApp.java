package com.example.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(HttpConfiguration.class)
public class SpringPlaygroundApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlaygroundApp.class, args);
    }

}
