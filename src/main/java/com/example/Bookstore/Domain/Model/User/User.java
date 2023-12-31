package com.example.Bookstore.Domain.Model.User;

import com.example.Bookstore.Domain.Model.Cart.CartItem;
import com.example.Bookstore.Domain.Model.Order.Order;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String firstName;
    private String lastName;
    @Column(name = "email",
            unique = true)
    @NaturalId(mutable = true)
    private String email;
    private String password;
    private String role;
    private String token;
    private boolean isEnabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + userID +
                ", name='" + firstName + '\'' +
                " email='" + email + '\'' +
                '}';
    }
}
