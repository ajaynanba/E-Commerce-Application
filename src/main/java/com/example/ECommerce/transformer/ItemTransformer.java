package com.example.ECommerce.transformer;

import com.example.ECommerce.dto.request.ItemRequestDto;
import com.example.ECommerce.dto.response.ItemResponseDto;
import com.example.ECommerce.entity.Item;

public class ItemTransformer {

    public static Item itemRequestToItem(ItemRequestDto itemRequestDto){

        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto itemToItemResponse(Item item){

        return ItemResponseDto.builder()
                .priceOfOneItem(item.getProduct().getPrice())
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .totalPrice(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }


}
