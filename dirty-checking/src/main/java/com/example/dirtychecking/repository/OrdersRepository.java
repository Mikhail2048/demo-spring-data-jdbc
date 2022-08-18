package com.example.dirtychecking.repository;

import com.example.dirtychecking.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}