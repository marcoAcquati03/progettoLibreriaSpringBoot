package com.example.progettoSpringBoot.Model;

import jakarta.persistence.*;

@Entity
public class UserLibro {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Libro libro;

    public UserLibro(){}

    public UserLibro(User user, Libro libro) {
        this.user = user;
        this.libro = libro;
    }


    public long getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setBook(Libro libro) {
        this.libro = libro;
    }

}
