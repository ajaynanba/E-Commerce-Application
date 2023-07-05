package com.example.ECommerce.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerResponseDto {

    String name;

    int age;

    String mobNo;

    String email;
}
