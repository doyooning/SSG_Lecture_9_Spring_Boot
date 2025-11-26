package com.dynii.doplyshop.cart.service;

import com.dynii.doplyshop.cart.dto.CartRead;
import com.dynii.doplyshop.cart.entity.Cart;
import com.dynii.doplyshop.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseCartService implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<CartRead> findAll(Integer memberId) {

        return cartRepository.findAllByMemberId(memberId).stream().map(Cart::toRead).toList();
    }

    @Override
    public CartRead find(Integer memberId, Integer itemId) {
        Optional<Cart> cartOptional = cartRepository.findByMemberIdAndItemId(memberId, itemId);
        return cartOptional.map(Cart::toRead).orElse(null);
    }

    @Transactional
    @Override
    public void removeAll(Integer memberId) {
        cartRepository.deleteByMemberId(memberId);
    }

    @Transactional
    @Override
    public void remove(Integer memberId, Integer itemId) {
        cartRepository.deleteByMemberIdAndItemId(memberId, itemId);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
