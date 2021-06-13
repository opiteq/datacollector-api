package com.alwaysup.tracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class TrackingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackingApiApplication.class, args);
    }

}
