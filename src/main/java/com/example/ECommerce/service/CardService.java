package com.example.ECommerce.service;

import com.example.ECommerce.Exception.CardAlreadyExist;
import com.example.ECommerce.dto.request.CardRequestDto;
import com.example.ECommerce.dto.response.CardResponseDto;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

    public CardResponseDto add(CardRequestDto cardRequestDto) throws CardAlreadyExist, CustomerAlreadyExist;
}
