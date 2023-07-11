package com.example.ECommerce.service.impl;


import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.dto.request.OrderRequestDto;
import com.example.ECommerce.dto.response.OrderResponseDto;
import com.example.ECommerce.entity.*;
import com.example.ECommerce.repository.CardRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.repository.OrderRepository;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.transformer.OrderTransformer;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl {

    @Autowired
    ProductServiceimpl productService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepository orderRepository;


    public Ordered placeOrder(Customer customer, Card card) throws Exception {

        Cart cart = customer.getCart();
        Ordered order = new Ordered();
        order.setOrderNO(String.valueOf(UUID.randomUUID()));
        order.setCardType(card.getCardType());
        order.setCustomer(customer);
        String maskedNo = generateMaskedNo(card.getCardNo());
        order.setCardUsed(maskedNo);


        List<Item> items = new ArrayList<>();
        for(Item item : cart.getItemList()){
            try {
                productService.decreaseQuantity(item);
                items.add(item);

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        order.setItemList(items);
        order.setTotalPrice(cart.getTotalPrice());
        return order;
    }

    public OrderResponseDto placeDirectOrder(OrderRequestDto orderRequestDto) throws Exception {

        Customer customer;
        try {
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new CustomerAlreadyExist("Customer does not exist");
        }
        Product product;
        try {
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new Exception("product does not exist");
        }
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card==null || card.getCvv()!=orderRequestDto.getCvv() || card.getCustomer()!=customer){
            throw new Exception("Your card is not valid!!");
        }
        Ordered order = OrderTransformer.orderRequestDtoToOrder(orderRequestDto, customer, card);
        Ordered savedOrder = orderRepository.save(order);

        OrderResponseDto orderResponseDto = OrderTransformer.orderToOrderResponseDto(savedOrder, customer);
        return orderResponseDto;
    }

    private static String generateMaskedNo(String cardNo) {
        String mask = "";
        for(int i = 0;i<cardNo.length()-1;i++){
            mask += "x";
        }
        mask += cardNo.substring(cardNo.length()-4);
        return mask;
    }
}
