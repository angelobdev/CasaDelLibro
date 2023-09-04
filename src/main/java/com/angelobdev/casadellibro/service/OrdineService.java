package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.*;
import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import com.angelobdev.casadellibro.repository.LibroRepository;
import com.angelobdev.casadellibro.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private CarrelloService carrelloService;

    @Autowired
    private SpedizioneService spedizioneService;

    @Autowired
    private RitiroService ritiroService;

    @Autowired
    private LibroRepository libroRepository;

    // CRUD

    public Ordine create(Integer carrelloID, Integer spedizioneID, Integer ritiroID) {

        // CHECK
        if (spedizioneID > 0 && ritiroID > 0) {
            throw new RuntimeException("Un ordine non puo' essere associato ad entrambi spedizione e ritiro!");
        }

        // Carico il carrello
        Carrello carrello = carrelloService.getById(carrelloID);
        carrello.setAcquistato(true);

        // Creo l'ordine
        Ordine ordine = new Ordine();
        ordine.setData(new Date());
        ordine.setCarrello(carrello);
        ordine.setImporto(carrello.getImporto());

        if (spedizioneID > 0) {
            Spedizione spedizione = spedizioneService.getById(spedizioneID);
            ordine.setSpedizione(spedizione);
        } else if (ritiroID > 0) {
            Ritiro ritiro = ritiroService.getById(ritiroID);
            ordine.setRitiro(ritiro);
        } else {
            throw new RuntimeException("Un ordine deve essere associato ad almeno una spedizione o un ritiro!");
        }

        // Decremento le quantita in magazzino
        for (CarrelloLibro cl : carrello.getLibri()) {
            Libro libro = cl.getLibro();
            libro.setQuantita(libro.getQuantita() - cl.getQuantita());
            libroRepository.save(libro);
        }

        return ordineRepository.save(ordine);

    }

    public List<Ordine> getAll() {
        return ordineRepository.findAll();
    }

    public Ordine getById(Integer ordineID) {
        return ordineRepository.findById(ordineID).orElse(null);
    }

}
