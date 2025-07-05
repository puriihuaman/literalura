package com.puriihuaman.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class LiteraluraApplication {
    public static void main(final String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }
}