package com.example.ECommerce.transformer;

import com.example.ECommerce.dto.request.CustomerRequestDto;
import com.example.ECommerce.dto.response.CustomerResponseDto;
import com.example.ECommerce.entity.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponse(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .build();
    }
}
