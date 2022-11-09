package com.projectspring.itemdonation.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projectspring.itemdonation.models.ItemModel;
import com.projectspring.itemdonation.repositories.ItemRepository;

@Service
public class ItemService {
    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemModel> obterItemAll() {
        return itemRepository.findAll();
    }
}
