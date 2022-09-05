package com.example.webflux;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.webflux.entity.Item;
import com.example.webflux.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class MongoDbSliceTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    void itemRepositorySavesItemsTest() {
        var sampleItem = Item.builder()
                .name("name")
                .description("description")
                .price(1.99)
                .build();

        itemRepository.save(sampleItem)
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.id()).isNotNull();
                    assertThat(item.name()).isEqualTo("name");
                    assertThat(item.description()).isEqualTo("description");
                    assertThat(item.price()).isEqualTo(1.99);

                    return true;
                })
                .verifyComplete();

    }
}
