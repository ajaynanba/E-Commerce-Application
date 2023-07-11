package com.example.ECommerce.controller;

import com.example.ECommerce.dto.request.CustomerRequestDto;
import com.example.ECommerce.dto.response.CustomerResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    CustomerServiceImpl customerService;
    @PostMapping("/add")
    public CustomerResponseDto add(@RequestBody CustomerRequestDto customerRequestDto) throws CustomerAlreadyExist {
        return customerService.add(customerRequestDto);
    }

    @GetMapping("/get/{emailOrMobNo}")
    public ResponseEntity<CustomerResponseDto> getByEmail(@PathVariable("emailOrMobNo") String emailOrmobNo) throws CustomerAlreadyExist {
        return new ResponseEntity(customerService.getByEmailOrMobNo(emailOrmobNo), HttpStatus.FOUND);
    }

    @GetMapping("/getByCard/{cardType}")
    public List<CustomerResponseDto> getByCard(@PathVariable("cardType") CardType cardType){

        return customerService.getByCard(cardType);
    }

}
