package com.example.ECommerce.transformer;

import com.example.ECommerce.dto.response.CartResponseDto;
import com.example.ECommerce.entity.Cart;

public class CartTransformer {

    public static CartResponseDto cartToCartResponse(Cart cart){

//        int  total = cart

        return CartResponseDto.builder()
                .totalPrice(cart.getTotalPrice())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();
    }
}
