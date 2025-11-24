package com.ssg.gallery.item.service;

import com.ssg.gallery.item.dto.ItemRead;

import java.util.List;

public class BaseItemService implements ItemService {

    @Override
    public List<ItemRead> findAll() {
        return List.of();
    }

    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return List.of();
    }
}
