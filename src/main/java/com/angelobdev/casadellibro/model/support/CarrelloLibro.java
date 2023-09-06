package com.angelobdev.casadellibro.model.support;

import com.angelobdev.casadellibro.model.Carrello;
import com.angelobdev.casadellibro.model.Libro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carrelli_libri")
@Getter
@Setter
public class CarrelloLibro {

    @Id
    @GeneratedValue(generator = "carrelli_libri_id_seq")
    @SequenceGenerator(name = "carrelli_libri_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrello_id")
    @JsonIgnore
    private Carrello carrello;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    @Column(name = "quantita")
    private Integer quantita;

}
