package com.dynii.doplyshop.item.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemRead {
    private Integer id;
    private String name;
    private String artist;
    private String description;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}
