package com.example.webflux;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.webflux.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ItemUnitTest {

    @Test
    void itemBasicsShouldWork() {
        Item item = new Item("item1", "TV tray", "Alf TV tray", 19.99);

        assertThat(item.id()).isEqualTo("item1");
        assertThat(item.name()).isEqualTo("TV tray");
        assertThat(item.description()).isEqualTo("Alf TV tray");
        assertThat(item.price()).isEqualTo(19.99);
    }
}
