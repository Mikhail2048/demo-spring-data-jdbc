package com.example.springdatajdbcexamples.models.one_to_x;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "ORDER_FILE")
@AllArgsConstructor
@NoArgsConstructor
public class OrderFile {

    @Id
    private Long id;
    private String name;
}