package com.app.bookfinder;

import com.app.bookfinder.model.Book;
import com.app.bookfinder.repository.BookRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class BookFinderApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookRepo;


    List<Book> bookList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BookFinderApplication.class, args);
    }

    @Override
    public void run(String @NonNull ... args) throws Exception {
        // Test: Save a book
        bookRepo.save(new Book("Java Basics", "Learn Java", "John Doe"));

        // Test: Print count
        System.out.println("Total books in DB: " + bookRepo.count());
    }
}
