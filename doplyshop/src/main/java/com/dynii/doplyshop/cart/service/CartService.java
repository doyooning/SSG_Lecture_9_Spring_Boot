package com.dynii.doplyshop.cart.service;

import com.dynii.doplyshop.cart.dto.CartRead;
import com.dynii.doplyshop.cart.entity.Cart;

import java.util.List;

public interface CartService {
    List<CartRead> findAll(Integer memberId);

    CartRead find(Integer memberId, Integer itemId);

    void removeAll(Integer memberId);

    void remove(Integer memberId, Integer itemId);

    void save(Cart cart);
}
