package com.example.Bookstore.Domain.Model.Cart;


import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Order.Order;
import com.example.Bookstore.Domain.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartitem")
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemID")
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Book book;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartItemList")
    private Set<Order> orders = new HashSet<>();

}
