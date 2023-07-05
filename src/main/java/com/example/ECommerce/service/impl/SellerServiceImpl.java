package com.example.ECommerce.service.impl;

import com.example.ECommerce.Exception.EmailAlreadyPresent;
import com.example.ECommerce.Exception.SellerNotFound;
import com.example.ECommerce.dto.request.ProductRequestDto;
import com.example.ECommerce.dto.request.SellerRequestDto;
import com.example.ECommerce.dto.response.SellerResponseDto;
import com.example.ECommerce.entity.Seller;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.SellerService;
import com.example.ECommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto add(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresent{

        Seller seller;
         try {
             seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
         }
         catch (Exception e){
             throw new EmailAlreadyPresent("Email Already Present");
         }
        sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResponseDto(seller);
    }

    public SellerResponseDto getByEmail(String  email) throws SellerNotFound {
        Seller seller;
        try {
            seller = sellerRepository.findByEmail(email);
        }
        catch (Exception e){
            throw new SellerNotFound("Seller does not exist");
        }
        return SellerTransformer.sellerToSellerResponseDto(seller);
    }

    public List<SellerResponseDto> getAll(){
        List<Seller> sellers = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for(Seller seller : sellers){
            sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
        }
        return sellerResponseDtos;
    }
}
