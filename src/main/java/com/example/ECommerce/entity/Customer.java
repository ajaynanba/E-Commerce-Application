package com.example.ECommerce.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int age;

    @Column(unique = true)
    String email;

    String mobNo;

    String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderList = new ArrayList<>();

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//    List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cardList = new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;

}
