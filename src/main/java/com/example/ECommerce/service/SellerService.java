package com.example.ECommerce.service;

import com.example.ECommerce.Exception.EmailAlreadyPresent;
import com.example.ECommerce.Exception.SellerNotFound;
import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.request.SellerRequestDto;
import com.example.ECommerce.dto.response.SellerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SellerService {

    public SellerResponseDto add(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresent;

    public SellerResponseDto getByEmail(String email) throws SellerNotFound;

    public List<SellerResponseDto> getAll();

}
