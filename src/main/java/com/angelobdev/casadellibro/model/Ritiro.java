package com.angelobdev.casadellibro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ritiri")
@Getter
@Setter
public class Ritiro {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "ritiri_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data_disponibile")
    private Date dataDisponibile;

    @Column(name = "data_ritiro")
    private Date dataRitiro;

    @Column(name = "stato")
    private String stato;
}
