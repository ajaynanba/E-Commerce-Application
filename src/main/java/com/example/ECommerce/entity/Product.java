package com.example.ECommerce.entity;


import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    int price;

    int quantity;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
}
