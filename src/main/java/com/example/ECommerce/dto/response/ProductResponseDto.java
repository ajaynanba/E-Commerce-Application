package com.example.ECommerce.dto.response;

import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

   String name;

   int price;

   int quantity;

   ProductStatus productStatus;

   ProductCategory productCategory;
}
