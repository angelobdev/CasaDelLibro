package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtentiRepository extends JpaRepository<Utente, Integer> {

    Optional<Utente> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
