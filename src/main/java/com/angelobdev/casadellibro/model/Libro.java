package com.angelobdev.casadellibro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "libri")
@Getter
@Setter
public class Libro {

    @Id
    @GeneratedValue(generator = "libri_id_seq")
    @SequenceGenerator(name = "libri_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "autore")
    private String autore;

    @Column(name = "numero_pagine")
    private Integer numeroPagine;

    @Column(name = "eta_minima")
    private Integer etaMinima;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "copertina")
    private String copertina;

    @Column(name = "prezzo")
    private Double prezzo;
}
