package com.example.ECommerce.controller;

import com.example.ECommerce.Exception.CardAlreadyExist;
import com.example.ECommerce.dto.request.CardRequestDto;
import com.example.ECommerce.dto.response.CardResponseDto;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.service.impl.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServiceImpl cardService;

    @PostMapping("/add")
    public CardResponseDto add(@RequestBody CardRequestDto cardRequestDto) throws CardAlreadyExist, CustomerAlreadyExist {
        return cardService.add(cardRequestDto);
    }
}
