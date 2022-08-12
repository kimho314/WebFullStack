package com.example.webflux.service;

import com.example.webflux.entity.Cart;
import com.example.webflux.entity.CartItem;
import com.example.webflux.repository.CartRepository;
import com.example.webflux.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    public Mono<Cart> addToCart(String cartId, String id) {
        return cartRepository.findById(cartId)
                .defaultIfEmpty(Cart.builder().id(cartId).build())
                .flatMap(cart -> cart.cartItems().stream()
                        .filter(cartItem -> cartItem.item()
                                .id().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            var index = cart.cartItems().indexOf(cartItem);
                            if (index != -1) {
                                cart.cartItems().remove(cartItem);
                                var increasedCartItem = cartItem.increment();
                                cart.cartItems().add(index, increasedCartItem);
                            }
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> itemRepository.findById(id)
                                .map(item -> CartItem.builder().item(item).build())
                                .doOnNext(cartItem -> cart.cartItems().add(cartItem))
                                .map(cartItem -> cart))
                )
                .flatMap(cartRepository::save);
    }
}
