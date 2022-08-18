package com.example.lazyloading;

import com.example.lazyloading.models.Order;
import com.example.lazyloading.models.OrderFile;
import com.example.lazyloading.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class LazyLoadingApplication implements ApplicationRunner {

    @Autowired
    private OrdersRepository ordersRepository;

    public static void main(String[] args) {
        SpringApplication.run(LazyLoadingApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Order> orders = ordersRepository.findById(1L);
        if (orders.isPresent()) {
            List<OrderFile> orderFiles = orders.get().getOrderFiles();
            System.out.println("Files are " + orderFiles);
        }
    }
}
