package com.example.SalesManagment.Model.Product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoriesID;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "categories")
    private Set<Product> productSet = new HashSet<>();

}
