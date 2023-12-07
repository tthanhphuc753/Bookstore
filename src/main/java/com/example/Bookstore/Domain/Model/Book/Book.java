package com.example.Bookstore.Domain.Model.Book;

import com.example.Bookstore.Domain.Model.User.User;
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
    @Column(name = "bookID")
    private long id;
    private double price;
    private String image;
    private String name;
    private String author;

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

    public Book(double price, String name, String author, Set<Categories> categoriesSet) {
        this.price = price;
        this.name = name;
        this.author = author;
        this.categoriesSet = categoriesSet;
    }

    public Book() {
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
