package org.example.audiobookS;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication// annotation of spring boot -- this is the entry point of running application
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
