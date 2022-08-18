package com.example.springdatajdbcexamples.models.one_to_x;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table(value = "ORDERS")
public class Order {

    @Id
    private Long id;

    private boolean isPaid;

    // To be discussed...
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ID")
    private Product product;

    // One To Many
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ID")
    private List<OrderFile> orderFiles;

    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ID")
    private OrderAttributes orderAttributes;
}