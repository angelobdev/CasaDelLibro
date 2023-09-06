package com.angelobdev.casadellibro.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
public class Ruolo {

    @Id
    @GeneratedValue(generator = "ruoli_id_seq")
    @SequenceGenerator(name = "ruoli_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "grado")
    private Integer grado;
}
