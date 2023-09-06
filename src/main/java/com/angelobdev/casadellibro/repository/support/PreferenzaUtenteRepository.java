package com.angelobdev.casadellibro.repository.support;

import com.angelobdev.casadellibro.model.support.PreferenzaUtente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferenzaUtenteRepository extends JpaRepository<PreferenzaUtente, Integer> {

    Optional<PreferenzaUtente> findByUtenteIdAndLibroId(Integer utenteID, Integer libroID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO preferenze_utenti (utente_id, libro_id) VALUES ( ?1 , ?2 )", nativeQuery = true)
    void like(Integer utenteID, Integer libroID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM preferenze_utenti WHERE utente_id = ?1 AND libro_id = ?2", nativeQuery = true)
    void dislike(Integer utenteID, Integer libroID);

}
