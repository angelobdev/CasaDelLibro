package com.angelobdev.casadellibro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "ordini")
@Getter
@Setter
public class Ordine {

    @Id
    @GeneratedValue
    @SequenceGenerator(name = "ordini_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Column(name = "importo")
    private Double importo;

    @OneToOne
    @JoinColumn(name = "carrello_id")
    private Carrello carrello;

    @OneToOne
    @JoinColumn(name = "spedizione_id")
    private Spedizione spedizione;

    @OneToOne
    @JoinColumn(name = "ritiro_id")
    private Ritiro ritiro;
}
