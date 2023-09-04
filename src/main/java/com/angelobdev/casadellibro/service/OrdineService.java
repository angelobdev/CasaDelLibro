package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

}
