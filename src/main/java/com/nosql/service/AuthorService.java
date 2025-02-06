package com.nosql.service;

import com.nosql.model.Author;
import com.nosql.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthorService {
    
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll() {

        log.info("Find all Authors");

        List<Author> Authors = authorRepository.findAll();
        return Authors;
    }

    public Author findById(Long id) {

        log.info("Find Author by Id: " + id);

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return author;
    }

    public Author create(Author author) {

        log.info("Create Author: {}", author);

        Author savedAuthor = save(author);

        return savedAuthor;
    }

    public Author update(Long id, Author author) {

        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Author with id %s not found", id));
        }

        Author existingAuthor = authorOptional.get();

        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());

        log.info("Update Author: {} with {}", existingAuthor, author);

        return save(author);
    }

    @Transactional
    public Author save(Author Author) {

        log.info("Save Author: " + Author);

        return authorRepository.save(Author);
    }

    @Transactional
    public void deleteById(Long id) {

        log.info("Delete Author by: " + id);

        authorRepository.deleteById(id);
    }
}
