package com.example.ECommerce.transformer;

import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.request.SellerRequestDto;
import com.example.ECommerce.dto.response.SellerResponseDto;
import com.example.ECommerce.entity.Seller;

public class SellerTransformer {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .email(sellerRequestDto.getEmail())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .email(seller.getEmail())
                .mobNo(seller.getMobNo())
                .build();
    }
}
