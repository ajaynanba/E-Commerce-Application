package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {

    Seller findByEmail(String email);

    List<Seller> findAll();
}
