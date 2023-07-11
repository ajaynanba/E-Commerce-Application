package com.example.ECommerce.transformer;


import com.example.ECommerce.dto.request.CardRequestDto;
import com.example.ECommerce.dto.response.CardResponseDto;
import com.example.ECommerce.entity.Card;
import com.example.ECommerce.enums.CardType;

public class CardTransformer {

    public static Card cardRequestToCard(CardRequestDto cardRequestDto){

       return Card.builder()
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .cardType(cardRequestDto.getCardType())
               .cardNo(cardRequestDto.getCardNo())
                .build();
    }

    public static CardResponseDto cardToCardResponse(Card card){

        return CardResponseDto.builder()
                .message("Card has been Added to " +card.getCustomer().getName()).build();

    }
}
