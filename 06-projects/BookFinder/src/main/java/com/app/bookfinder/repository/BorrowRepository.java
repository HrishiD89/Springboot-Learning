package com.app.bookfinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowRepository extends MongoRepository<Borrow,String> {
}
