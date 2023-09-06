package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Spedizione;
import com.angelobdev.casadellibro.repository.SpedizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SpedizioneService {

    @Autowired
    private SpedizioneRepository spedizioneRepository;

    // CRUD

    public Spedizione create(String indirizzo) {
        Spedizione spedizione = new Spedizione();
        spedizione.setDestinazione(indirizzo);

        ZoneId systemZone = ZoneId.systemDefault();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Date domani = Date.from(tomorrow.atStartOfDay(systemZone).toInstant());

        spedizione.setDataSpedizione(domani);
        spedizione.setStato("IN ELABORAZIONE");

        return spedizioneRepository.save(spedizione);
    }

    public Spedizione getById(Integer spedizioneID){
        return spedizioneRepository.findById(spedizioneID).orElse(null);
    }
}
