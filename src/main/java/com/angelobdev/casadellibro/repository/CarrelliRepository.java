package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Carrello;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelliRepository extends JpaRepository<Carrello, Integer> {

    Optional<Carrello> findCarrelloByUtenteId(Integer utenteID);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO carrelli_libri (carrello_id , libro_id) VALUES (?1 , ?2) LIMIT 1", nativeQuery = true)
    void aggiungiLibro(Integer carrelloID, Integer libroID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carrelli_libri WHERE ctid IN( SELECT ctid FROM carrelli_libri WHERE (carrello_id = ?1 AND libro_id = ?2) LIMIT 1)", nativeQuery = true)
    void rimuoviLibro(Integer carrelloID, Integer libroID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carrelli_libri WHERE carrello_id = ?1", nativeQuery = true)
    void svuotaCarrello(Integer carrelloID);

}
