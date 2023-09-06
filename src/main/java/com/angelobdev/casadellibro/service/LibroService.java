package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Libro;
import com.angelobdev.casadellibro.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    // CRUD

    public Libro getById(Integer id) {
        return libroRepository.findById(id).orElse(null);
    }

    public List<Libro> getAll() {
        return libroRepository.findAllByOrderByTitoloAsc();
    }

}
