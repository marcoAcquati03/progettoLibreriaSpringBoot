package com.example.progettoSpringBoot.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Libri")
public class Libro {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Questo campo non può essere vuoto")
    String autore;



    @NotNull(message = "Questo campo non può essere vuoto") String titolo;
    @NotNull(message = "Questo campo non può essere vuoto") String annoPubblicazione;
    @NotNull(message = "Questo campo non può essere vuoto")
    Double prezzo;

    @OneToMany(mappedBy = "libro", fetch=FetchType.EAGER)
    public Set<UserLibro> userLibri = new HashSet<>();

    public Libro(){}

    public Libro(String autore, String titolo, String annoPubblicazione, Double prezzo) {
        this.autore = autore;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(String annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "autore='" + autore + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione='" + annoPubblicazione + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }


}
