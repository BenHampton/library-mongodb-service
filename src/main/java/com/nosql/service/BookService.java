package com.nosql.service;

import com.nosql.model.Book;
import com.nosql.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {
    
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {

        log.info("Find all books");

        List<Book> books = bookRepository.findAll();
        return books;
    }

    public Book findById(Long id) {

        log.info("Find book by Id: " + id);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return book;
    }

    public Book create(Book book) {

        log.info("Create book: {}", book);

        Book savedBook = save(book);

        return savedBook;
    }

    public Book update(Long id, Book book) {

        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id %s not found", id));
        }

        Book existingBook = bookOptional.get();

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthorId(book.getAuthorId());

        log.info("Update book: {} with {}", existingBook, book);

        return save(book);
    }

    @Transactional
    public Book save(Book book) {

        log.info("Save Book: " + book);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteById(Long id) {

        log.info("Delete Book by: " + id);

        bookRepository.deleteById(id);
    }
}
