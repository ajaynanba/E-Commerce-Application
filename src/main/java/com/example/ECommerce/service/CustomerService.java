package com.example.ECommerce.service;

import com.example.ECommerce.dto.request.CustomerRequestDto;
import com.example.ECommerce.dto.response.CustomerResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    public CustomerResponseDto add(CustomerRequestDto customerRequestDto) throws CustomerAlreadyExist;

    public CustomerResponseDto getByEmailOrMobNo(String emailOrMobNo) throws CustomerAlreadyExist;

    public List<CustomerResponseDto> getByCard(CardType cardType);
}
