package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Carrello;
import com.angelobdev.casadellibro.model.Libro;
import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import com.angelobdev.casadellibro.repository.CarrelliRepository;
import com.angelobdev.casadellibro.repository.LibriRepository;
import com.angelobdev.casadellibro.service.support.CarrelliLibriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarrelliService {

    @Autowired
    private UtentiService utentiService;

    @Autowired
    private CarrelliRepository carrelliRepository;

    @Autowired
    private LibriRepository libriRepository;

    @Autowired
    private CarrelliLibriService carrelliLibriService;

    public Carrello createCarrello(Integer utenteID) {
        Carrello carrello = new Carrello();

        carrello.setDataCreazione(new Date());
        carrello.setImporto(0.0d);
        carrello.setAcquistato(false);
        carrello.setLibri(new ArrayList<>());

        Utente utente = utentiService.getById(utenteID);
        assert utente != null;

        carrello.setUtente(utente);
        return carrelliRepository.save(carrello);
    }

    public Carrello aggiungiLibro(Integer carrelloID, Integer libroID, Integer quantitaDaAggiungere) {

        CarrelloLibro carrelloLibro = carrelliLibriService.getCarrelloLibroByIds(carrelloID, libroID);

        // Se il libro è gia presente
        Carrello carrello;
        Libro libro;


        if (carrelloLibro != null) {
            // Libro già presente nel carrello
            carrello = carrelloLibro.getCarrello();
            libro = carrelloLibro.getLibro();
        } else {

            // Libro non ancora presente nel carrello
            carrello = carrelliRepository.findById(carrelloID).orElse(null);
            libro = libriRepository.findById(libroID).orElse(null);


        }

        // Aggiungo al carrello
        carrelliLibriService.aggiungiLibro(carrelloID, libroID, quantitaDaAggiungere);

        // Aggiorno importo
        assert carrello != null && libro != null;
        double nuovoImporto = carrello.getImporto() + (libro.getPrezzo() * quantitaDaAggiungere);
        carrello.setImporto(nuovoImporto);

        return carrelliRepository.save(carrello);
    }

    public Carrello rimuoviLibro(Integer carrelloID, Integer libroID, Integer quantitaDaRimuovere) {
        CarrelloLibro carrelloLibro = carrelliLibriService.getCarrelloLibroByIds(carrelloID, libroID);
        assert carrelloLibro != null;

        Carrello carrello = carrelloLibro.getCarrello();
        Libro libro = carrelloLibro.getLibro();

        carrelliLibriService.rimuoviLibro(carrelloID, libroID, quantitaDaRimuovere);

        double importo = carrello.getImporto() - (libro.getPrezzo() * quantitaDaRimuovere);
        carrello.setImporto(importo);

        return carrelliRepository.save(carrello);
    }

    public Carrello svuotaCarrello(Integer carrelloID) {
        carrelliLibriService.svuotaCarrello(carrelloID);

        Carrello carrello = carrelliRepository.findById(carrelloID).orElse(null);
        assert carrello != null;

        carrello.setImporto(0.0);
        return carrelliRepository.save(carrello);
    }

    public Carrello getCarelloUtente(Integer utenteID) {
        return carrelliRepository.findCarrelloByUtenteId(utenteID).orElse(null);
    }

    public List<Carrello> getAll() {
        return carrelliRepository.findAll();
    }

    public void deleteCarello(Integer carrelloID) {
        carrelliRepository.deleteById(carrelloID);
    }

}
