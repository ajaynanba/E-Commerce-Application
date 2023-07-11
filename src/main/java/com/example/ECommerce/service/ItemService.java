package com.example.ECommerce.service;


import com.example.ECommerce.dto.request.ItemRequestDto;
import com.example.ECommerce.entity.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
