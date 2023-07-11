package com.example.ECommerce.dto.response;


import com.example.ECommerce.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    String customerName;

    CardType cardType;

    @CreationTimestamp
    Date date;

    String cardUsed;

    int totalPrice;

    List<ItemResponseDto> items;

}
