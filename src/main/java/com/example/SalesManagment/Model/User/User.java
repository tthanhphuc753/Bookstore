package com.example.SalesManagment.Model.User;

import com.example.SalesManagment.Model.Product.Product;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;
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
        if (dateOfBirth != null) {
            return Period.between(dateOfBirth, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userID +
                ", name='" + firstName + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                '}';
    }
}
