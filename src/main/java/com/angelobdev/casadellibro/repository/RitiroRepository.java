package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Ritiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RitiroRepository extends JpaRepository<Ritiro, Integer> {
}
