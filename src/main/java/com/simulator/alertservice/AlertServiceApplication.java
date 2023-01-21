package com.simulator.alertservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AlertServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertServiceApplication.class, args);
        log.info("Running...");
    }

}
