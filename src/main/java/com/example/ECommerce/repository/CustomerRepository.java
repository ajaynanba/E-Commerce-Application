package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Customer;
import com.example.ECommerce.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

    Customer findByMobNo(String mobNo);

//    @Query(value = "select p from customer p where p.cardType=:cardType")
//    List<Customer> findByCardType(CardType cardType);
}
