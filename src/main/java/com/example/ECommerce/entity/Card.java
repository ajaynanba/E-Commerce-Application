package com.example.ECommerce.entity;


import com.example.ECommerce.enums.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    int cvv;

    Date expiryDate;

    @ManyToOne
    @JoinColumn
    Customer customer;

}
