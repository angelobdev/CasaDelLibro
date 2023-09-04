package com.angelobdev.casadellibro.service.support;

import com.angelobdev.casadellibro.model.support.CarrelloLibro;
import com.angelobdev.casadellibro.repository.CarrelliRepository;
import com.angelobdev.casadellibro.repository.LibriRepository;
import com.angelobdev.casadellibro.repository.support.CarrelliLibriRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class CarrelliLibriService {

    @Autowired
    private CarrelliLibriRepository carrelliLibriRepository;

    @Autowired
    private CarrelliRepository carrelliRepository;

    @Autowired
    private LibriRepository libriRepository;

    @Modifying
    @Transactional
    public void aggiungiLibro(Integer carrelloID, Integer libroID, Integer quantita) {

        CarrelloLibro clmod = carrelliLibriRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);

        if (clmod != null) {

            clmod.setQuantita(clmod.getQuantita() + quantita);

        } else {

            CarrelloLibro carrelloLibro = new CarrelloLibro();
            carrelloLibro.setCarrello(carrelliRepository.findById(carrelloID).orElse(null));
            carrelloLibro.setLibro(libriRepository.findById(libroID).orElse(null));
            carrelloLibro.setQuantita(quantita);
            carrelliLibriRepository.save(carrelloLibro);

        }

    }

    @Modifying
    @Transactional
    public void rimuoviLibro(Integer carrelloID, Integer libroID, Integer quantitaDaRimuovere) {

        CarrelloLibro clmod = carrelliLibriRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);
        assert clmod != null;

        if (clmod.getQuantita() - quantitaDaRimuovere <= 0) {
            carrelliLibriRepository.eliminaLibro(carrelloID, libroID);
        } else {

            clmod.setQuantita(clmod.getQuantita() - quantitaDaRimuovere);
            carrelliLibriRepository.save(clmod);

        }

    }

    public CarrelloLibro getCarrelloLibroByIds(Integer carrelloID, Integer libroID) {
        return carrelliLibriRepository.findByCarrelloIdAndLibroId(carrelloID, libroID).orElse(null);
    }

    public void svuotaCarrello(Integer carrelloID) {
        carrelliLibriRepository.svuotaCarrello(carrelloID);
    }

}
