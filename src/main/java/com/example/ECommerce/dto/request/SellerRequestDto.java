package com.example.ECommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class SellerRequestDto {

    String name;

    int age;

    String email;

    String mobNo;
}
