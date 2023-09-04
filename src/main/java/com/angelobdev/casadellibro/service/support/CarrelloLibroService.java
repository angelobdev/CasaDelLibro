package com.angelobdev.casadellibro.service.support;

import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import com.angelobdev.casadellibro.repository.CarrelloRepository;
import com.angelobdev.casadellibro.repository.LibroRepository;
import com.angelobdev.casadellibro.repository.support.CarrelloLibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class CarrelloLibroService {

    @Autowired
    private CarrelloLibroRepository carrelloLibroRepository;

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private LibroRepository libroRepository;

    // CRUD

    public CarrelloLibro getByCarrelloIdAndLibroId(Integer carrelloID, Integer libroID) {
        return carrelloLibroRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);
    }

    // METHODS

    @Modifying
    @Transactional
    public void aggiungiLibro(Integer carrelloID, Integer libroID, Integer quantita) {

        CarrelloLibro clmod = carrelloLibroRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);

        if (clmod != null) {

            clmod.setQuantita(clmod.getQuantita() + quantita);

        } else {

            CarrelloLibro carrelloLibro = new CarrelloLibro();
            carrelloLibro.setCarrello(carrelloRepository.findById(carrelloID).orElse(null));
            carrelloLibro.setLibro(libroRepository.findById(libroID).orElse(null));
            carrelloLibro.setQuantita(quantita);
            carrelloLibroRepository.save(carrelloLibro);

        }

    }

    @Modifying
    @Transactional
    public void rimuoviLibro(Integer carrelloID, Integer libroID, Integer quantitaDaRimuovere) {

        CarrelloLibro clmod = carrelloLibroRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);
        assert clmod != null;

        if (clmod.getQuantita() - quantitaDaRimuovere <= 0) {
            carrelloLibroRepository.eliminaLibro(carrelloID, libroID);
        } else {

            clmod.setQuantita(clmod.getQuantita() - quantitaDaRimuovere);
            carrelloLibroRepository.save(clmod);

        }

    }

    @Modifying
    @Transactional
    public void svuotaCarrello(Integer carrelloID) {
        carrelloLibroRepository.svuotaCarrello(carrelloID);
    }

}
