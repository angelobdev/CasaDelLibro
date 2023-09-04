package com.angelobdev.casadellibro.repository.support;

import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelliLibriRepository extends JpaRepository<CarrelloLibro, Integer> {

    Optional<CarrelloLibro> findByCarrelloIdAndLibroId(Integer carrelloID, Integer libroID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carrelli_libri WHERE ctid IN( SELECT ctid FROM carrelli_libri WHERE (carrello_id = ?1 AND libro_id = ?2) LIMIT 1)", nativeQuery = true)
    void eliminaLibro(Integer carrelloID, Integer libroID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM carrelli_libri WHERE carrello_id = ?1", nativeQuery = true)
    void svuotaCarrello(Integer carrelloID);

}
