package com.example.springdatajdbcexamples.models.many_to_x;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table(name = "BOOK_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails {

    @Id
    private Long id;
    private String description;
    private Date writtenIn;
}