package com.dynii.doplyshop.item.service;

import com.dynii.doplyshop.item.dto.ItemRead;
import com.dynii.doplyshop.item.entity.Item;
import com.dynii.doplyshop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseItemService implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<ItemRead> findAll() {
        return itemRepository.findAll().stream().map(Item::toRead).toList();
    }

    @Override
    public List<ItemRead> findAll(List<Integer> ids) {
        return itemRepository.findAllById(ids).stream().map(Item::toRead).toList();
    }
}
