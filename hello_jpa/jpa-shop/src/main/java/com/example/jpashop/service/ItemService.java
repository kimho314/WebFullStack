package com.example.jpashop.service;

import com.example.jpashop.domain.Item;
import com.example.jpashop.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
