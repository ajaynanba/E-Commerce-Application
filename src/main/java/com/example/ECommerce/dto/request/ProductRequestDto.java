package com.example.ECommerce.dto.request;


import com.example.ECommerce.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    String name;

    int price;

    int quantity;

    ProductCategory productCategory;

    int sellerId;


}
