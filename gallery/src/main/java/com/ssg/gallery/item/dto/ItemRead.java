package com.ssg.gallery.item.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ItemRead {
    private Integer id;
    private String name;
    private String imgPath;
    private Integer price;
    private Integer discountPer;
}
