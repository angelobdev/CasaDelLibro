package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    Optional<Carrello> findByUtenteId(Integer utenteID);

}
