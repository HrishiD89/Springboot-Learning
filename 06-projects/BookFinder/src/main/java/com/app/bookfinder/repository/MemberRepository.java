package com.app.bookfinder.repository;

import com.app.bookfinder.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member,String> {}
