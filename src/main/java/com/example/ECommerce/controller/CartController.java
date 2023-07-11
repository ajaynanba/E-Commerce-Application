package com.example.ECommerce.controller;

import com.example.ECommerce.dto.request.CheckOutRequestDto;
import com.example.ECommerce.dto.request.ItemRequestDto;
import com.example.ECommerce.dto.response.CartResponseDto;
import com.example.ECommerce.dto.response.OrderResponseDto;
import com.example.ECommerce.entity.Cart;
import com.example.ECommerce.entity.Item;
import com.example.ECommerce.service.impl.CartServiceImpl;
import com.example.ECommerce.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {


    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    CartServiceImpl cartService;
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ItemRequestDto itemRequestDto) throws Exception{

        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(itemRequestDto.getCustomerId(), savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkOut")
    public ResponseEntity checkOut(@RequestBody CheckOutRequestDto checkOutRequestDto){
        try {
            OrderResponseDto orderResponseDto = cartService.checkOut(checkOutRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
