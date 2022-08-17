package com.example.webflux;

import com.example.webflux.entity.Cart;
import com.example.webflux.entity.CartItem;
import com.example.webflux.entity.Item;
import com.example.webflux.repository.CartRepository;
import com.example.webflux.repository.ItemRepository;
import com.example.webflux.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@Slf4j
@ExtendWith(SpringExtension.class)
public class CartServiceTest {

    private CartService cartService;
    @MockBean
    private ItemRepository itemRepository;
    @MockBean
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {

        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = CartItem.builder().item(sampleItem).build();
        Cart sampleCart = Cart.builder().id("My Cart").cartItems(Collections.singletonList(sampleCartItem)).build();

        Mockito.when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
        Mockito.when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        Mockito.when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

        cartService = new CartService(itemRepository, cartRepository);
    }

    @Test
    void addItemToEmptyCartShouldProductOneCartItem() {
        cartService.addToCart("My Cart", "item1")
                .as(StepVerifier::create) // StepVerifier : 테스트 도구가 구독하여 결과 확인 해준다.
                .expectNextMatches(cart -> {
                    assertThat(cart.cartItems())
                            .extracting(cartItems -> cartItems.stream().map(CartItem::quantity).collect(Collectors.toList()))
                            .asList()
                            .containsExactlyInAnyOrder(1);

                    assertThat(cart.cartItems())
                            .extracting(cartItems -> cartItems.stream().map(CartItem::item).collect(Collectors.toList()))
                            .asList()
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));

                    return Boolean.TRUE;
                })
                .verifyComplete(); // onCompletion 시그널을 확인하여 동작을 테스트 한다.
    }

    @Test
    void alternativeWayToTestItemToEmptyCartShouldProductOneCartItem() {
        StepVerifier.create(
                        cartService.addToCart("My Cart", "item1")
                                .log()
                )
                .expectNextMatches(cart -> {
                    assertThat(cart.cartItems())
                            .extracting(cartItems -> cartItems.stream().map(CartItem::quantity).collect(Collectors.toList()))
                            .asList()
                            .containsExactlyInAnyOrder(1);

                    assertThat(cart.cartItems())
                            .extracting(cartItems -> cartItems.stream().map(CartItem::item).collect(Collectors.toList()))
                            .asList()
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));

                    return Boolean.TRUE;
                })
                .verifyComplete(); // 테스트 도구가 구독하여 결과 확인 해준다.
    }
}
