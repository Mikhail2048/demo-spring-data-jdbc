package com.example.lazyloading.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class OrderFile {

    @Id
    private Long id;
    private String name;
    private long size;

    @ManyToOne
    private Order order;
}