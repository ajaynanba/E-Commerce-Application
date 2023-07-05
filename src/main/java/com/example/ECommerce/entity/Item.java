package com.example.ECommerce.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int requiredQuantity;


    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    Ordered orders;

    @ManyToOne
    @JoinColumn
    Product product;


}
