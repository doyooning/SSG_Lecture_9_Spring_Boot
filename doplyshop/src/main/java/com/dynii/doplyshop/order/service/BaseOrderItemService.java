package com.dynii.doplyshop.order.service;

import com.dynii.doplyshop.order.entity.OrderItem;
import com.dynii.doplyshop.order.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseOrderItemService implements OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll(Integer orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

    @Override
    public void saveAll(List<OrderItem> orderItems) {
        orderItemRepository.saveAll(orderItems);
    }
}
