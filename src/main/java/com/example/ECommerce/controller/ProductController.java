package com.example.ECommerce.controller;


import com.example.ECommerce.Exception.SellerNotFound;
import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.response.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.service.ProductService;
import com.example.ECommerce.service.impl.ProductServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductServiceimpl productService;
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ProductRequestDto productRequestDto) throws SellerNotFound {
        try{
            ProductResponseDto productResponseDto = productService.add(productRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ProductResponseDto productResponseDto = productService.add(productRequestDto);
        return new ResponseEntity(productResponseDto,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{category}")
    public List<ProductResponseDto> getByCategory(@PathVariable("category") ProductCategory category){
        return productService.getByCategory(category);
    }


}
