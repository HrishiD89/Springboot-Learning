package com.app.bookfinder.repository;

import com.app.bookfinder.model.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowRepository extends MongoRepository<Borrow,String> {
}
