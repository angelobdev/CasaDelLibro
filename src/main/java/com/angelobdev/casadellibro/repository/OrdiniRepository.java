package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdiniRepository extends JpaRepository<Ordine, Integer> {
}
