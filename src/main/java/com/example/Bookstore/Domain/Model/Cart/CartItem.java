package com.example.Bookstore.Domain.Model.Cart;


import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.User.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    private Integer id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "UserID",nullable = true)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Book book;

}
