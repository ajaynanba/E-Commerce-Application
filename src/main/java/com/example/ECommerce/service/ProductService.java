package com.example.ECommerce.service;

import com.example.ECommerce.Exception.SellerNotFound;
import com.example.ECommerce.controller.ProductController;
import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.response.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ProductResponseDto add(ProductRequestDto productRequestDto) throws SellerNotFound;

    public List<ProductResponseDto> getByCategory(ProductCategory productCategory);
}
