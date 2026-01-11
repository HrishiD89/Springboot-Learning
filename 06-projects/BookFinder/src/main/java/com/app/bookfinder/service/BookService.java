package com.app.bookfinder.service;

import com.app.bookfinder.model.Book;
import com.app.bookfinder.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(String id){
        return bookRepository.findById(id);
    }

    public Book updateBook(String id,Book bookDetails){
        return bookRepository.findById(id).map(book->{
            book.setTitle(bookDetails.getTitle());
            book.setDescription(bookDetails.getDescription());
            book.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(book);
        }).orElseThrow(()-> new RuntimeException("Book not found with id: "+id));
    }

    public void deleteBook(String id){
        bookRepository.deleteById(id);
    }
}
