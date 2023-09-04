package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Spedizione;
import com.angelobdev.casadellibro.repository.SpedizioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SpedizioniService {

    @Autowired
    private SpedizioniRepository spedizioniRepository;

    public Spedizione creaSpedizione(String indirizzo) {
        Spedizione spedizione = new Spedizione();
        spedizione.setDestinazione(indirizzo);

        ZoneId systemZone = ZoneId.systemDefault();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Date domani = Date.from(tomorrow.atStartOfDay(systemZone).toInstant());

        spedizione.setDataSpedizione(domani);
        spedizione.setStato("IN ELABORAZIONE");

        return spedizioniRepository.save(spedizione);
    }
}
