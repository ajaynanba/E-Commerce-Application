package com.example.ECommerce.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true)
    String email;

    int age;

    String mobNo;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();

}
