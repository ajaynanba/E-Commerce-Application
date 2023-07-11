package com.example.ECommerce.dto.request;


import com.example.ECommerce.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardRequestDto {

    int cvv;

    Date expiryDate;

    String cardNo;

    int customerId;

    CardType cardType;
}
