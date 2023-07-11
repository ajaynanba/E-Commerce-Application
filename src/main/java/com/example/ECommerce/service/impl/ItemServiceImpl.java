package com.example.ECommerce.service.impl;

import com.example.ECommerce.Exception.ProductNotExist;
import com.example.ECommerce.dto.request.ItemRequestDto;
import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.entity.Item;
import com.example.ECommerce.entity.Product;
import com.example.ECommerce.Exception.CustomerAlreadyExist;
import com.example.ECommerce.enums.ProductStatus;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.repository.ItemRepository;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.service.ItemService;
import com.example.ECommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {

        Customer customer;
        try {
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).orElseThrow(() -> new CustomerAlreadyExist("Customer does not exist"));
        }catch (Exception e){
            throw new CustomerAlreadyExist("Customer does not exist");
        }

        Product product;
        try {
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new ProductNotExist("Product Does not exist");
        }
        if(itemRequestDto.getRequiredQuantity() > product.getQuantity() || product.getProductStatus() != ProductStatus.IN_STOCK){
            throw new Exception("product or product quantity is not available");
        }

        Item item = ItemTransformer.itemRequestToItem(itemRequestDto);
        item.setProduct(product);

        product.getItemList().add(item);
        Item savedItem = itemRepository.save(item);
        return savedItem;

    }
}
