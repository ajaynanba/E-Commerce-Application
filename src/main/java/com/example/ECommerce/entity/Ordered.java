package com.example.ECommerce.entity;


import com.example.ECommerce.enums.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ordered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    int totalPrice;

    @CreationTimestamp
    Date date;

    String cardUsed;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();


}
