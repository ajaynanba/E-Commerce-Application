package com.example.ECommerce.service.impl;

import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.dto.request.CheckOutRequestDto;
import com.example.ECommerce.dto.response.CartResponseDto;
import com.example.ECommerce.dto.response.ItemResponseDto;
import com.example.ECommerce.dto.response.OrderResponseDto;
import com.example.ECommerce.entity.*;
import com.example.ECommerce.repository.CardRepository;
import com.example.ECommerce.repository.CartRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.repository.OrderRepository;
import com.example.ECommerce.service.CartService;
import com.example.ECommerce.transformer.CartTransformer;
import com.example.ECommerce.transformer.ItemTransformer;
import com.example.ECommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public CartResponseDto saveCart(int customerId, Item item) {

        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        if (cart == null) {
            cart = new Cart();
        }

        cart.setCustomer(customer);
        customer.setCart(cart);
        cart.getItemList().add(item);

        int totalPrice = 0;
        for (Item item1 : cart.getItemList()) {
            totalPrice += item1.getRequiredQuantity() * item1.getProduct().getPrice();
        }

        cart.setTotalPrice(totalPrice);
        cart.setNumberOfItems(cart.getItemList().size());
        item.setCart(cart);

        Cart savedCart = cartRepository.save(cart);
        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponse(savedCart);

        List<ItemResponseDto> itemResponseDto = new ArrayList<>();
        for(Item item1 : cart.getItemList()){
            itemResponseDto.add(ItemTransformer.itemToItemResponse(item1));
        }
        cartResponseDto.setItemResponseDtoList(itemResponseDto);
        return cartResponseDto;
    }

    public OrderResponseDto checkOut(CheckOutRequestDto checkOutRequestDto) throws Exception {

        Customer customer;
        try {
            customer = customerRepository.findById(checkOutRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new CustomerAlreadyExist("customer does not exist");
        }
        Card card  = cardRepository.findByCardNo(checkOutRequestDto.getCardNO());
        if(card==null || card.getCvv()!=checkOutRequestDto.getCvv() || checkOutRequestDto.getCustomerId()!= customer.getId()){
            throw new Exception("card is invalid");
        }
        Cart cart = customer.getCart();
        if(cart.getNumberOfItems()==0){
            throw new Exception("Cart is Empty");
        }

        try {
            Ordered order = orderService.placeOrder(customer, card);
            customer.getOrderList().add(order);
            reset(cart);
            Ordered savedOrder = orderRepository.save(order);

            OrderResponseDto orderResponseDto = OrderTransformer.orderToOrderResponseDto(order, customer);
            return orderResponseDto;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void reset(Cart cart){
        cart.setNumberOfItems(0);
        cart.setTotalPrice(0);
        cart.setItemList(new ArrayList<>());
    }
}
