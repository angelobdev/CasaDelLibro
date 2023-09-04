package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Ritiro;
import com.angelobdev.casadellibro.repository.RitiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RitiroService {

    @Autowired
    private RitiroRepository ritiroRepository;

    // CRUD

    public Ritiro create(Date dataScelta){
        Ritiro ritiro = new Ritiro();
        ritiro.setDataDisponibile(dataScelta);
        ritiro.setStato("IN PREPARAZIONE");
        return ritiroRepository.save(ritiro);
    }

}
