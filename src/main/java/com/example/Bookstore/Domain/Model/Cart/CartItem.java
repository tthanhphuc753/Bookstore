package com.example.Bookstore.Domain.Model.Cart;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartitem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long bookID;
    private String name;
    private double price;
    private int quantity;


}
