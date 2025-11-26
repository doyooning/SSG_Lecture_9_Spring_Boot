package com.dynii.doplyshop.order.service;

import com.dynii.doplyshop.cart.service.CartService;
import com.dynii.doplyshop.item.dto.ItemRead;
import com.dynii.doplyshop.item.service.ItemService;
import com.dynii.doplyshop.order.dto.OrderRead;
import com.dynii.doplyshop.order.dto.OrderRequest;
import com.dynii.doplyshop.order.entity.Order;
import com.dynii.doplyshop.order.entity.OrderItem;
import com.dynii.doplyshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BaseOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ItemService itemService;
    private final CartService cartService;

    @Override
    public List<OrderRead> findAll(Integer memberId) {
        return orderRepository.findAllByMemberIdOrderByIdDesc(memberId).stream().map(Order::toRead).toList();
    }

    @Override
    public OrderRead find(Integer id, Integer memberId) {
        Optional<Order> orderOptional = orderRepository.findByIdAndMemberId(id, memberId);

        if (orderOptional.isPresent()) {
            // 주문 조회 DTO로 변환
            OrderRead order = orderOptional.get().toRead();

            // 주문 상품 목록 조회
            List<OrderItem> orderItems = orderItemService.findAll(order.getId());

            // 주문 상품 목록의 상품 아이디를 추출
            List<Integer> orderItemIds = orderItems.stream().map(OrderItem::getItemId).toList();

            // 주문 상품 리스트의 상품 ID에 해당하는 상품 목록을 조회
            List<ItemRead> items = itemService.findAll(orderItemIds);

            // 응답 값에 상품 리스트 데이터를 설정
            order.setItems(items);

            return order;
        }
        return null;
    }

    @Transactional
    @Override
    public void order(OrderRequest orderReq, Integer memberId) {
        // 주문 상품의 최종 결제 금액을 계산
        List<ItemRead> items = itemService.findAll(orderReq.getItemIds());
        long amount = 0;

        for (ItemRead item : items) {
            amount += item.getPrice() - item.getPrice().longValue() * item.getDiscountPer() / 100;
        }
        // 주문 요청에 최종 결제 금액 입력
        orderReq.setAmount(amount);

        // 주문 저장
        Order order = orderRepository.save(orderReq.toEntity(memberId));

        // 주문 상품 데이터 생성
        List<OrderItem> newOrderItems = new ArrayList<>();

        // 상품 아이디만큼 주문 상품 추가
        orderReq.getItemIds().forEach((itemId) -> {
            OrderItem newOrderItem = new OrderItem(order.getId(), itemId);
            newOrderItems.add(newOrderItem);
        });

        // 주문 상품 데이터 저장
        orderItemService.saveAll(newOrderItems);

        // 장바구니 데이터 삭제(특정 회원)
        cartService.removeAll(order.getMemberId());
    }
}
