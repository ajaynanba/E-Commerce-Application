package com.example.ECommerce.transformer;


import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.response.ProductResponseDto;
import com.example.ECommerce.entity.Product;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class ProductTransformer {

    public static Product productRequestDtotoProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.IN_STOCK)
                .quantity(productRequestDto.getQuantity())
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productCategory(product.getProductCategory())
                .productStatus(product.getProductStatus())
                .build();

    }
}
