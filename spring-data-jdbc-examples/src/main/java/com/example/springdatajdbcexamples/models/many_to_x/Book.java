package com.example.springdatajdbcexamples.models.many_to_x;

import com.example.springdatajdbcexamples.models.many_to_x.Author.AuthorBookRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table(name = "BOOK")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private Long id;
    private String name;

    @Column("AMOUNT_OF_PAGES")
    private Integer amountOfPages;

    @MappedCollection(idColumn = "BOOK_ID", keyColumn = "ID")
    private BookDetails bookDetails;

    @MappedCollection(idColumn = "BOOK_ID", keyColumn = "AUTHOR_ID")
    private List<AuthorBookRef> authorRefs;
}