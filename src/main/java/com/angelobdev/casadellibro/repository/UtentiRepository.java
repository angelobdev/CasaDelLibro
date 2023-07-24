package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, Integer> {
}
