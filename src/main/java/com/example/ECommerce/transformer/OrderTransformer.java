package com.example.ECommerce.transformer;

import com.example.ECommerce.dto.request.OrderRequestDto;
import com.example.ECommerce.dto.response.ItemResponseDto;
import com.example.ECommerce.dto.response.OrderResponseDto;
import com.example.ECommerce.entity.*;
import com.example.ECommerce.service.impl.OrderServiceImpl;
import com.example.ECommerce.service.impl.ProductServiceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
        //this is class not used

    public static Ordered orderRequestDtoToOrder(OrderRequestDto orderRequestDto, Customer customer, Card card) throws Exception {

        ProductServiceimpl productService = new ProductServiceimpl();
        Cart cart = customer.getCart();
        List<Item> items = new ArrayList<>();
        for(Item item : cart.getItemList()){
            try {
                productService.decreaseQuantity(item);
                items.add(item);
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }

        return Ordered.builder()
                .orderNO(String.valueOf(UUID.randomUUID()))
                .cardUsed(generateMaskedNo(orderRequestDto.getCardNo()))
                .cardType(card.getCardType())
                .customer(customer)
                .totalPrice(cart.getTotalPrice())
                .itemList(items)
                .build();
    }
    public static OrderResponseDto orderToOrderResponseDto(Ordered order, Customer customer){

        String customerName = customer.getName();
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : order.getItemList()){
            itemResponseDtoList.add(ItemTransformer.itemToItemResponse(item));
        }
        return OrderResponseDto.builder()
                .cardType(order.getCardType())
                .cardUsed(order.getCardUsed())
                .date(order.getDate())
                .customerName(customerName)
                .items(itemResponseDtoList)
                .totalPrice(order.getTotalPrice())
                .build();
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
