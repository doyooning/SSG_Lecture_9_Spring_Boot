package com.dynii.doplyshop.cart.controller;

import com.dynii.doplyshop.account.helper.AccountHelper;
import com.dynii.doplyshop.cart.dto.CartRead;
import com.dynii.doplyshop.cart.dto.CartRequest;
import com.dynii.doplyshop.cart.service.CartService;
import com.dynii.doplyshop.item.dto.ItemRead;
import com.dynii.doplyshop.item.service.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ItemService itemService;
    private final AccountHelper accountHelper;

    @GetMapping("/api/cart/items")
    public ResponseEntity<?> readAll(HttpServletRequest request) {
        Integer memberId = accountHelper.getMemberId(request);
        List<CartRead> carts = cartService.findAll(memberId);
        List<Integer> itemIds = carts.stream().map(CartRead::getItemId).toList();
        List<ItemRead> items = itemService.findAll(itemIds);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/api/carts")
    public ResponseEntity<?> push(HttpServletRequest request, @RequestBody CartRequest cartRequest) {
        Integer memberId = accountHelper.getMemberId(request);
        CartRead cart = cartService.find(memberId, cartRequest.getItemId());
        if (cart == null) {
            cartService.save(cartRequest.toEntity(memberId));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/cart/items/{itemId}")
    public ResponseEntity<?> remove(HttpServletRequest request, @PathVariable("itemId") Integer itemId) {
        Integer memberId = accountHelper.getMemberId(request);
        cartService.remove(memberId, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
