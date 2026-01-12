package com.app.bookfinder.controller;

import com.app.bookfinder.model.Book;
import com.app.bookfinder.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Value("${app.description}")
    private String appDescription;

    @Autowired
    private BookService bookService;

    // POST: Create a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // GET: Retrieve all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET: Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        return bookService.getBookById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // PUT: Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

}
