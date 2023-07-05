package com.example.ECommerce.controller;


import com.example.ECommerce.Exception.EmailAlreadyPresent;
import com.example.ECommerce.Exception.SellerNotFound;
import com.example.ECommerce.dto.request.SellerRequestDto;
import com.example.ECommerce.dto.response.SellerResponseDto;
import com.example.ECommerce.service.SellerService;
import com.example.ECommerce.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add")
    public SellerResponseDto add(@RequestBody SellerRequestDto sellerRequestDto) throws EmailAlreadyPresent {
        return sellerService.add(sellerRequestDto);
    }

    @GetMapping("/getByEmail")
    public SellerResponseDto getByEmail(@RequestParam("email") String email) throws SellerNotFound {
        return sellerService.getByEmail(email);
    }

    @GetMapping("/getAll")
    public List<SellerResponseDto> getAll(){
        return sellerService.getAll();
    }
}
