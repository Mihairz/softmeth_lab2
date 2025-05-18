package com.example.demo.service;

import com.example.demo.model.Item;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getAll() {
        return repository.findAll();
    }

    public Item getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item create(Item item) {
        return repository.save(item);
    }

    public Item update(Long id, Item item) {
        Item existing = getById(id);
        existing.setName(item.getName());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
