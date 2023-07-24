package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.repository.LibriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibriService {

    @Autowired
    private LibriRepository libriRepository;

}
