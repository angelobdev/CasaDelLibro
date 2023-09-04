package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Ritiro;
import com.angelobdev.casadellibro.repository.RitiriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RitiriService {

    @Autowired
    private RitiriRepository ritiriRepository;

    public Ritiro creaRitiro(Date dataScelta){
        Ritiro ritiro = new Ritiro();
        ritiro.setDataDisponibile(dataScelta);
        ritiro.setStato("IN PREPARAZIONE");
        return ritiriRepository.save(ritiro);
    }

}
