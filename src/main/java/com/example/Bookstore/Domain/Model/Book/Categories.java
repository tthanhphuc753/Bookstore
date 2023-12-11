package com.example.Bookstore.Domain.Model.Book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoriesID;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categoriesSet", cascade = CascadeType.ALL)
    private Set<Book> bookSet = new HashSet<>();


    public Categories( String name, Set<Book> bookSet) {
        this.name = name ;
        this.bookSet = bookSet;
    }
}
