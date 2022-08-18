package com.example.springdatajdbcexamples.models.one_to_x;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@Table(name = "ORDER_ATTRIBUTES")
@AllArgsConstructor
@NoArgsConstructor
public class OrderAttributes {

    private Long id;
    private String description;
    private Date createdAt;
    private Long ownerId;
}