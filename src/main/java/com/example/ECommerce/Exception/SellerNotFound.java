package com.example.ECommerce.Exception;

import com.example.ECommerce.repository.SellerRepository;

public class SellerNotFound extends Exception{

    public SellerNotFound(String message){
        super(message);
    }
}
