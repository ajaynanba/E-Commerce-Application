package com.example.ECommerce.service.impl;

import com.example.ECommerce.dto.request.CustomerRequestDto;
import com.example.ECommerce.dto.response.CustomerResponseDto;
import com.example.ECommerce.entity.Card;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.repository.CardRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CustomerService;
import com.example.ECommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public CustomerResponseDto add(CustomerRequestDto customerRequestDto) throws CustomerAlreadyExist {
        Customer customer = customerRepository.findByEmail(customerRequestDto.getEmail());
        if(customer!=null){
            throw new CustomerAlreadyExist("Customer Already Exist!!");
        }
        Customer savedCustomer = CustomerTransformer.customerRequestToCustomer(customerRequestDto);
         customerRepository.save(savedCustomer);

        //prepare CustomerResponse
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponseDto getByEmailOrMobNo(String emailOrMobNo) throws CustomerAlreadyExist {
        Customer customerByEmail = customerRepository.findByEmail(emailOrMobNo);
        Customer customerMobNo = customerRepository.findByMobNo(emailOrMobNo);
        if(customerByEmail==null && customerMobNo==null){
            throw new CustomerAlreadyExist("Customer does not exist!!");
        }
        if(customerByEmail!=null){
            return CustomerTransformer.customerToCustomerResponse(customerByEmail);
        }

        return CustomerTransformer.customerToCustomerResponse(customerMobNo);
    }

    public List<CustomerResponseDto> getByCard(CardType cardType){

        List<Card> cards = cardRepository.findByCardType(cardType);
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for(Card card : cards){
            CustomerTransformer.customerToCustomerResponse(card.getCustomer());
        }
        return customerResponseDtos;
    }
}
