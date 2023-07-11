package com.example.ECommerce.repository;

import com.example.ECommerce.entity.Card;
import com.example.ECommerce.enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCvv(int cvv);

    List<Card> findByCardType(CardType cardType);

    Card findByCardNo(String cardNo);
}
