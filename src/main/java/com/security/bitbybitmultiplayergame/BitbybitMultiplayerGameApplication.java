package com.security.bitbybitmultiplayergame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.security.bitbybitmultiplayergame", "handler"})
public class BitbybitMultiplayerGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitbybitMultiplayerGameApplication.class, args);
    }

}
