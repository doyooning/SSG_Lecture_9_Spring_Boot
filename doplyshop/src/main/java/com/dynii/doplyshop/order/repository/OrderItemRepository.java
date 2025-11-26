package com.dynii.doplyshop.order.repository;

import com.dynii.doplyshop.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> { // ①

    // 주문 상품 목록 조회
    List<OrderItem> findAllByOrderId(Integer orderId); // ②
}