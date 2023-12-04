package com.example.SalesManagment.Domain.Model.Book;

import com.example.SalesManagment.Domain.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookID;
    private double price;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "bookList", cascade = CascadeType.ALL)
    private Set<User> userList = new HashSet<>();


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_cate",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "categoriesID")
    )
    private Set<Categories> categoriesSet = new HashSet<>();

    public Book(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Book() {

    }


    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + bookID +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
