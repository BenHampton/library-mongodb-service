package com.nosql.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("book")
public class Book {

    @Id
    private Long id;

    private Long authorId;

    private String title;

    @DocumentReference(lookup = "self.authorId")
    private Author author;

}
