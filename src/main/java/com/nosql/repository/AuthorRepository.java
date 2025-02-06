package com.nosql.repository;

import com.nosql.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, Long> {
}
