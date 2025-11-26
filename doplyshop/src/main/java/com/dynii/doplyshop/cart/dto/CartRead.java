package com.dynii.doplyshop.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartRead {

    private Integer id;
    private Integer itemId;
}
