package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.repository.SpedizioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpedizioniService {

    @Autowired
    private SpedizioniRepository spedizioniRepository;
}
