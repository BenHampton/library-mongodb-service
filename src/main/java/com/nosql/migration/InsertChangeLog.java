package com.nosql.migration;

import com.nosql.model.Author;
import com.nosql.model.Book;
import com.nosql.repository.AuthorRepository;
import com.nosql.repository.BookRepository;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;

import java.util.List;

@ChangeUnit(order = "002", id = "create and insert data for Author & Book tables", author = "bham")
public class InsertChangeLog {

    @Execution
    public void executionMethodName(AuthorRepository authorRepository, BookRepository bookRepository) {

        System.out.println("execution changelog 002 - create and insert data for Author & Book tables");

        Author authorOne = Author.builder()
                .id(100L)
                .firstName("Testy")
                .lastName("McTester")
                .build();

        Author authorTwo = Author.builder()
                .id(200L)
                .firstName("Jim")
                .lastName("Pickles")
                .build();

        Book bookOne = Book.builder()
                .id(1L)
                .authorId(authorOne.getId())
                .title("Test Title One")
                .build();

        Book bookTwo = Book.builder()
                .id(2L)
                .authorId(authorOne.getId())
                .title("Test Title Teo")
                .build();

        authorRepository.saveAll(List.of(authorOne, authorTwo));
        bookRepository.saveAll(List.of(bookOne, bookTwo));
    }

    @RollbackExecution
    public void rollbackExecutionMethodName() {
        System.out.println("rollback execution seeding database..."); //todo
    }
}
