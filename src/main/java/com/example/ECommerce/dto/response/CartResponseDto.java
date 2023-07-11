package com.example.ECommerce.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDto {

    int totalPrice;

    int numberOfItems;

    String customerName;

    List<ItemResponseDto> itemResponseDtoList;
}
