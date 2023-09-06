package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    @Query(value = "SELECT * FROM carrelli c WHERE (c.acquistato = false AND c.utente_id = ?1)", nativeQuery = true)
    Optional<Carrello> findNonAcquistatoByUtente(Integer utenteID);

}
