package com.app.bookfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BookFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookFinderApplication.class, args);
    }
}
