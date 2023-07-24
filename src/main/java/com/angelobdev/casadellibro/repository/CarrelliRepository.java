package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrelliRepository extends JpaRepository<Carrello, Integer> {
}
