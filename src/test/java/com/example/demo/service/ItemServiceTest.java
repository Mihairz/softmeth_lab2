package com.example.demo.service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_Found() {
        Item item = new Item(1L, "Test");
        when(repository.findById(1L)).thenReturn(Optional.of(item));
        assertEquals("Test", service.getById(1L).getName());
    }

    @Test
    void testGetById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.getById(1L));
    }

    @Test
    void testCreate() {
        Item item = new Item(null, "New");
        Item saved = new Item(1L, "New");
        when(repository.save(item)).thenReturn(saved);
        assertEquals(1L, service.create(item).getId());
    }
}
