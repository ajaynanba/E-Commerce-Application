package com.example.ECommerce.entity;


import com.example.ECommerce.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    @Column(unique = true)
    String cardNo;

    int cvv;

    Date expiryDate;

    @ManyToOne
    @JoinColumn
    Customer customer;

}
