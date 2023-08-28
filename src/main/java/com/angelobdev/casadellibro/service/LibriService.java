package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Libro;
import com.angelobdev.casadellibro.repository.LibriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibriService {

    @Autowired
    private LibriRepository libriRepository;

    public List<Libro> getAllLibri() {
        return libriRepository.findAll();
    }

}
