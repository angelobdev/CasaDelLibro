package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    List<Libro> findAllByOrderByTitoloAsc();
}
