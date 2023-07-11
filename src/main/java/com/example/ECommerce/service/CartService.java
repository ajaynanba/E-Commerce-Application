package com.example.ECommerce.service;


import com.example.ECommerce.dto.response.CartResponseDto;
import com.example.ECommerce.entity.Item;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public CartResponseDto saveCart(int customerId, Item item);
}
