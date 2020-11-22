package ru.yegorpilipenko.otus.architect.healthcheck;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class HealthCheck {

    public static void main(final String... args) {
        run(HealthCheck.class, args);
    }

}
