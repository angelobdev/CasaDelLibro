package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Carrello;
import com.angelobdev.casadellibro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibriRepository extends JpaRepository<Libro, Integer> {
}
