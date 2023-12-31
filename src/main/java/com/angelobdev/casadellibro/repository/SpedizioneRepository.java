package com.angelobdev.casadellibro.repository;

import com.angelobdev.casadellibro.model.Spedizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpedizioneRepository extends JpaRepository<Spedizione, Integer> {
}
