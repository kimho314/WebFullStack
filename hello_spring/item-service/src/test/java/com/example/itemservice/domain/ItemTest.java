package com.example.itemservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    void create() {
        //given

        //when
        Item item = new Item();
        //then
        assertThat(item).isNotNull();
    }

    @Test
    void create2() {
        //given
        Long id = 1L;
        String itemName = "testItem";
        Integer price = 10_000;
        Integer quantity = 1;

        //when
        Item item = new Item(id, itemName, price, quantity);

        //then
        assertThat(item).isNotNull();
        assertThat(item.getId()).isEqualTo(id);
        assertThat(item.getItemName()).isEqualTo(itemName);
        assertThat(item.getPrice()).isEqualTo(price);
        assertThat(item.getQuantity()).isEqualTo(quantity);
    }
}
