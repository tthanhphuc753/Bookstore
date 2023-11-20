package com.example.SalesManagment.Model.User;

import com.example.SalesManagment.Model.Product.Product;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.time.Period;
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
    private String name;
    @Transient
    private int age;
    private LocalDate dateOfBirth;
    @Column(name = "email",
            unique = true)
    @NaturalId(mutable = true)
    private String email;
    private String password;
    private String role;
    private String token;
    private boolean isEnabled = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "productID")
    )
    private Set<Product> productList = new HashSet<>();


    // Getters and Setters

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }


    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }
}
