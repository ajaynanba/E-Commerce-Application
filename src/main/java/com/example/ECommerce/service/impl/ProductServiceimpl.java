package com.example.ECommerce.service.impl;

import com.example.ECommerce.Exception.SellerNotFound;
import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.response.ProductResponseDto;
import com.example.ECommerce.entity.Product;
import com.example.ECommerce.entity.Seller;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.ProductService;
import com.example.ECommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceimpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductResponseDto add(ProductRequestDto productRequestDto) throws SellerNotFound {
        Seller seller;

        try {
            seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        }
        catch (Exception e){
            throw new SellerNotFound("Seller does not exist");
        }
        Product product = ProductTransformer.productRequestDtotoProduct(productRequestDto);
        productRepository.save(product);

        return ProductTransformer.productToProductResponseDto(product);
    }

    public List<ProductResponseDto> getByCategory(ProductCategory productCategory){
        List<Product> products = productRepository.findByProductCategory(productCategory);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products){
            productResponseDtos.add(ProductTransformer.productToProductResponseDto(product));
        }
        return productResponseDtos;
    }
}
