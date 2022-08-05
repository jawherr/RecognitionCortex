package com.neocortex.recognitioncortex.service;

import com.neocortex.recognitioncortex.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Page<Order> findByStatus(Integer status, Pageable pageable);

    Page<Order> findByBuyerEmail(String email, Pageable pageable);

    Page<Order> findByBuyerPhone(String phone, Pageable pageable);

    Order findOne(Long orderId);


    Order finish(Long orderId);

    Order cancel(Long orderId);
}
