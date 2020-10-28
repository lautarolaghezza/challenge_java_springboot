package com.challenge.java.spring.wolox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.challenge.java.spring.wolox")
public class WoloxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WoloxApplication.class, args);
    }

}
