package com.example.dirtychecking.models;

import lombok.Getter;

@Getter
public class Payment {

    private final Long id;
    private final Long orderId;

    public Payment(Long id, Long orderId) {
        this.id = id;
        this.orderId = orderId;
    }
}