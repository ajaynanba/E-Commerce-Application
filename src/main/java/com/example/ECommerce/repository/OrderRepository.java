package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordered, Integer> {


}
