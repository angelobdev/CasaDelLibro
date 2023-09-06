package com.angelobdev.casadellibro.model.support;

import com.angelobdev.casadellibro.model.Libro;
import com.angelobdev.casadellibro.model.Utente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "preferenze_utenti")
@Getter
@Setter
public class PreferenzaUtente {

    @Id
    @GeneratedValue(generator = "preferenze_utenti_id_seq")
    @SequenceGenerator(name = "preferenze_utenti_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id")
    private Libro libro;

}
