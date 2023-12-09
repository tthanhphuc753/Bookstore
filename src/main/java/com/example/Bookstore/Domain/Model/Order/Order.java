package com.example.Bookstore.Domain.Model.Order;

import com.example.Bookstore.Domain.Model.User.User;
import com.example.Bookstore.Domain.Model.Book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    private long id;
    private Date date;
    private String bookName;
    private int quantity;


    @ManyToOne
    @JoinColumn(name="userID", nullable=false)
    private User user;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_book",
            joinColumns = @JoinColumn(name = "orderID"),
            inverseJoinColumns = @JoinColumn(name = "bookID")
    )
    private Set<Book> bookList = new HashSet<>();

    public Order(Set<Book> book,Date date, String bookName, User user, int quantity) {
        this.bookList = book;
        this.date = date;
        this.bookName = bookName;
        this.user = user;
        this.quantity = quantity;
    }

    public Order() {
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", Book='" + bookName + '\'' +
                '}';
    }
}
