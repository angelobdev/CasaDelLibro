package com.angelobdev.casadellibro.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "spedizioni")
@Getter
@Setter
public class Spedizione {

    @Id
    @GeneratedValue(generator = "spedizioni_id_seq")
    @SequenceGenerator(name = "spedizioni_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "destinazione")
    private String destinazione;

    @Column(name = "data_spedizione")
    private Date dataSpedizione;

    @Column(name = "data_consegna")
    private Date dataConsegna;

    @Column(name = "stato")
    private String stato;

}
