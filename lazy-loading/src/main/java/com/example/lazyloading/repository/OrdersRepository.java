package com.example.lazyloading.repository;

import com.example.lazyloading.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {

}