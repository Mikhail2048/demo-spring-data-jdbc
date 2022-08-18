package com.example.dirtychecking.repository;

import com.example.dirtychecking.models.Payment;
import org.springframework.stereotype.Component;

@Component
public class CassandraRepository {

    public Payment getPaymentByOrderId(Long orderId) {
        return new Payment(10L, orderId);
    }
}