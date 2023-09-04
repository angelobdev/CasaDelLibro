package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Carrello;
import com.angelobdev.casadellibro.model.Libro;
import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import com.angelobdev.casadellibro.repository.CarrelloRepository;
import com.angelobdev.casadellibro.repository.LibroRepository;
import com.angelobdev.casadellibro.service.support.CarrelloLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CarrelloLibroService carrelloLibroService;

    // CRUD

    public Carrello create(Integer utenteID) {
        Carrello carrello = new Carrello();

        carrello.setDataCreazione(new Date());
        carrello.setImporto(0.0d);
        carrello.setAcquistato(false);
        carrello.setLibri(new ArrayList<>());

        Utente utente = utenteService.getById(utenteID);
        assert utente != null;

        carrello.setUtente(utente);
        return carrelloRepository.save(carrello);
    }

    public List<Carrello> getAll() {
        return carrelloRepository.findAll();
    }

    public Carrello getByUtenteId(Integer utenteID) {
        return carrelloRepository.findByUtenteId(utenteID).orElse(null);
    }

    public void delete(Integer carrelloID) {
        carrelloRepository.deleteById(carrelloID);
    }


    // METHODS

    public Carrello aggiungiLibro(Integer carrelloID, Integer libroID, Integer quantitaDaAggiungere) {

        // Carico la relazione
        CarrelloLibro carrelloLibro = carrelloLibroService.getByCarrelloIdAndLibroId(carrelloID, libroID);

        Carrello carrello;
        Libro libro;

        if (carrelloLibro != null) {
            // Libro già presente nel carrello
            carrello = carrelloLibro.getCarrello();
            libro = carrelloLibro.getLibro();
        } else {

            // Libro non ancora presente nel carrello
            carrello = carrelloRepository.findById(carrelloID).orElse(null);
            libro = libroRepository.findById(libroID).orElse(null);
        }

        // Aggiungo al carrello
        carrelloLibroService.aggiungiLibro(carrelloID, libroID, quantitaDaAggiungere);

        // Aggiorno importo
        assert carrello != null && libro != null;
        double nuovoImporto = carrello.getImporto() + (libro.getPrezzo() * quantitaDaAggiungere);
        carrello.setImporto(nuovoImporto);

        return carrelloRepository.save(carrello);
    }

    public Carrello rimuoviLibro(Integer carrelloID, Integer libroID, Integer quantitaDaRimuovere) {

        // Carico la relazione
        CarrelloLibro carrelloLibro = carrelloLibroService.getByCarrelloIdAndLibroId(carrelloID, libroID);

        Carrello carrello = carrelloLibro.getCarrello();
        Libro libro = carrelloLibro.getLibro();

        // Rimuovo dal carrello
        carrelloLibroService.rimuoviLibro(carrelloID, libroID, quantitaDaRimuovere);

        // Aggiorno l'importo
        double importo = carrello.getImporto() - (libro.getPrezzo() * quantitaDaRimuovere);
        carrello.setImporto(importo);

        return carrelloRepository.save(carrello);
    }

    public Carrello svuotaCarrello(Integer carrelloID) {

        // Svuoto il carrello
        carrelloLibroService.svuotaCarrello(carrelloID);

        Carrello carrello = carrelloRepository.findById(carrelloID).orElse(null);
        assert carrello != null;

        // Aggiorno l'importo
        carrello.setImporto(0.0);
        return carrelloRepository.save(carrello);
    }
}
