package com.angelobdev.casadellibro.model;

import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carrelli")
@Getter
@Setter
public class Carrello {

    @Id
    @GeneratedValue(generator = "carrelli_id_seq")
    @SequenceGenerator(name = "carrelli_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_creazione")
    private Date dataCreazione;

    @Column(name = "importo")
    private Double importo;

    @Column(name = "acquistato")
    private Boolean acquistato;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

//    @OneToMany
//    @JoinTable(name = "carrelli_libri",
//            joinColumns = @JoinColumn(name = "carrello_id"),
//            inverseJoinColumns = @JoinColumn(name = "libro_id"))
//    private List<Libro> libri;

    @OneToMany(mappedBy = "carrello")
    private List<CarrelloLibro> libri;


}