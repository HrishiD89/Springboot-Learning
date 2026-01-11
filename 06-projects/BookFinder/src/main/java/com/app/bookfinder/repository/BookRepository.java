package com.app.bookfinder.repository;

import com.app.bookfinder.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String> {
    @Query("{title:'?0'}")
    Book findBooksByTitle(String title);

    @Query(value="{author:'?0'}", fields="{'title' : 1, 'description' : 1}")
    List<Book> findAllByAuthor(String author);

    public long count();
}
