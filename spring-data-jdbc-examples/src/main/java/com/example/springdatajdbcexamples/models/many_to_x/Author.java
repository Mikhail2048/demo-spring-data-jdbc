package com.example.springdatajdbcexamples.models.many_to_x;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "AUTHOR")
public class Author {

    @Id
    private Long id;
    private String name;
    private String country;

    @Getter
    @Table(name = "AUTHORS_BOOKS")
    public static class AuthorBookRef {

        @Column("AUTHOR_ID") private final Long authorId;
        @Column("BOOK_ID") private final Long bookId;

        public AuthorBookRef(Long authorId, Long bookId) {
            this.authorId = authorId;
            this.bookId = bookId;
        }
    }
}