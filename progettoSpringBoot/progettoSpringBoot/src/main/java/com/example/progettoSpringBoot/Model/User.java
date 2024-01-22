package com.example.progettoSpringBoot.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Questo campo non può essere vuoto")
    @Size(min = 3, max = 15, message = "Questo campo deve essere lungo tra 3 e 15 caratteri")
    String nome, cognome, username, password;

    public Set<UserLibro> getUserLibri() {
        return userLibri;
    }

    public void setUserLibri(Set<UserLibro> userLibri) {
        this.userLibri = userLibri;
    }


    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
    private Set<UserLibro> userLibri = new HashSet<>();

    public User(){}

    public User(String nome, String cognome, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



}
