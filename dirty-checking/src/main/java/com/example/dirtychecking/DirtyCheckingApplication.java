package com.example.dirtychecking;

import com.example.dirtychecking.models.Order;
import com.example.dirtychecking.models.Payment;
import com.example.dirtychecking.repository.CassandraRepository;
import com.example.dirtychecking.repository.OrdersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class DirtyCheckingApplication implements ApplicationRunner {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CassandraRepository cassandraRepository;

    public static void main(String[] args) {
        SpringApplication.run(DirtyCheckingApplication.class, args);
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Order> orderOptional = ordersRepository.findById(1L);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            Payment payment = cassandraRepository.getPaymentByOrderId(order.getId());

            if (payment != null) {
                order.setIsPaid(true);
            }
        }
    }
}
