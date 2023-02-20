package com.example.webflux;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.webflux.controller.HomeController;
import com.example.webflux.entity.Cart;
import com.example.webflux.entity.Item;
import com.example.webflux.repository.mongodb.CartRepository;
import com.example.webflux.repository.mongodb.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(HomeController.class)
public class HomeControllerSliceTest {

    @Autowired
    private WebTestClient client;

    //    @MockBean
//    InventoryService inventoryService;
    @MockBean
    CartRepository cartRepository;
    @MockBean
    ItemRepository itemRepository;

    @Test
    void homePageTest() {
        Mockito.when(itemRepository.findAll())
                .thenReturn(Flux.just(
                        Item.builder().id("id1").name("name1").description("desc1").price(1.99).build(),
                        Item.builder().id("id2").name("name2").description("desc2").price(3.99).build()
                ));
        Mockito.when(cartRepository.findById("My Cart"))
                .thenReturn(Mono.just(Cart.builder().id("My Cart").build()));

        client.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(exchangeResult -> {
                    assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id1\"");
                    assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id2\"");
                });
    }
}
