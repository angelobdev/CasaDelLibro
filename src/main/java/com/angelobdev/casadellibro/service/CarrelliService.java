package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.*;
import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import com.angelobdev.casadellibro.repository.*;
import com.angelobdev.casadellibro.repository.support.CarrelliLibriRepository;
import com.angelobdev.casadellibro.service.support.CarrelliLibriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarrelliService {

    @Autowired
    private UtentiRepository utentiRepository;

    @Autowired
    private CarrelliRepository carrelliRepository;

    @Autowired
    private LibriRepository libriRepository;

    @Autowired
    private CarrelliLibriService carrelliLibriService;

    @Autowired
    private OrdiniRepository ordiniRepository;

    @Autowired
    private SpedizioniRepository spedizioniRepository;

    @Autowired
    private RitiriRepository ritiriRepository;
    @Autowired
    private CarrelliLibriRepository carrelliLibriRepository;

    public Carrello createCarrello(Integer utenteID) {
        Carrello carrello = new Carrello();

        carrello.setDataCreazione(new Date());
        carrello.setImporto(0.0d);
        carrello.setAcquistato(false);
        carrello.setLibri(new ArrayList<>());

        Utente utente = utentiRepository.findById(utenteID).orElse(null);
        assert utente != null;

        carrello.setUtente(utente);
        return carrelliRepository.save(carrello);
    }

    public Carrello aggiungiLibro(Integer carrelloID, Integer libroID, Integer quantitaDaAggiungere) {

        CarrelloLibro carrelloLibro = carrelliLibriRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);

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

    public Carrello rimuoviLibro(Integer carrelloID, Integer libroID) {
        CarrelloLibro carrelloLibro = carrelliLibriRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);
        assert carrelloLibro != null;

        Carrello carrello = carrelloLibro.getCarrello();
        Libro libro = carrelloLibro.getLibro();
        Integer quantita = carrelloLibro.getQuantita();

        double importo = carrello.getImporto() - (libro.getPrezzo() * quantita);
        carrello.setImporto(importo);

        carrelliLibriService.rimuoviLibro(carrelloID, libroID);

        return carrelliRepository.save(carrello);
    }

    public Carrello svuotaCarrello(Integer carrelloID) {
        carrelliLibriService.svuotaCarrello(carrelloID);

        Carrello carrello = carrelliRepository.findById(carrelloID).orElse(null);
        assert carrello != null;

        carrello.setImporto(0.0);
        return carrelliRepository.save(carrello);
    }

    public Ordine acquistaCarrello(Integer carrelloID, Integer spedizioneID, Integer ritiroID) {

        if (spedizioneID > 0 && ritiroID > 0)
            throw new RuntimeException("Un ordine non può essere associato a entrambi: spedizione e ritiro");

        // Cerco il carrello da acquistare
        Carrello carrello = carrelliRepository.findById(carrelloID).orElse(null);
        assert carrello != null;

        // Creo l'ordine
        Ordine ordine = new Ordine();
        ordine.setData(new Date());
        ordine.setImporto(carrello.getImporto());
        ordine.setCarrello(carrello);

        // Assegno spedizione se esiste
        if (spedizioneID > 0) {
            Spedizione spedizione = spedizioniRepository.findById(spedizioneID).orElse(null);
            ordine.setSpedizione(spedizione);
        }

        // Assegno ritiro se esiste
        if (ritiroID > 0) {
            Ritiro ritiro = ritiriRepository.findById(ritiroID).orElse(null);
            ordine.setRitiro(ritiro);
        }

        carrello.setAcquistato(true);

        carrelliRepository.save(carrello);
        return ordiniRepository.save(ordine);

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
