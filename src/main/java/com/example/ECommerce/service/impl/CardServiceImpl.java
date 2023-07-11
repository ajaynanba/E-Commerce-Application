package com.example.ECommerce.service.impl;

import com.example.ECommerce.Exception.CardAlreadyExist;
import com.example.ECommerce.dto.request.CardRequestDto;
import com.example.ECommerce.dto.response.CardResponseDto;
import com.example.ECommerce.entity.Card;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.repository.CardRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CardService;
import com.example.ECommerce.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Override
    public CardResponseDto add(CardRequestDto cardRequestDto) throws CardAlreadyExist, CustomerAlreadyExist {
        Card card = cardRepository.findByCvv(cardRequestDto.getCvv());
        if(card!=null){
            throw new CardAlreadyExist("Card Already Exist");
        }
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new CustomerAlreadyExist("Customer does not exist");
        }

        Card savedCard = CardTransformer.cardRequestToCard(cardRequestDto);
        savedCard.setCustomer(customer);
        customer.getCardList().add(savedCard);
        customerRepository.save(customer);

        return CardTransformer.cardToCardResponse(savedCard);
    }
}
