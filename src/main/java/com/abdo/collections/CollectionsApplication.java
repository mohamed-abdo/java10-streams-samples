package com.abdo.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectionsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionsApplication.class);

    public static void main(String[] args) {
        LOGGER.info("application starting ....");
        SpringApplication.run(
                CollectionsApplication.class, args);
        LOGGER.info("application started.");
    }
}
