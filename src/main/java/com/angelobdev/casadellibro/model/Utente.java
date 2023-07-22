package com.angelobdev.casadellibro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "utenti")
@Getter
@Setter
public class Utente {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "utenti_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    private Date dataNascita;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;

    @OneToMany
    @JoinTable(name = "preferenze_utenti", joinColumns = @JoinColumn(name = "utente_id"), inverseJoinColumns = @JoinColumn(name = "libro_id"))
    private List<Libro> libriPreferiti;
}
