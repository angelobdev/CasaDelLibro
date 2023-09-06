package com.angelobdev.casadellibro.service.support;

import com.angelobdev.casadellibro.model.support.PreferenzaUtente;
import com.angelobdev.casadellibro.repository.support.PreferenzaUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferenzaUtenteService {

    @Autowired
    private PreferenzaUtenteRepository preferenzaUtenteRepository;

    public boolean toggleLike(Integer utenteID, Integer libroID) {

        PreferenzaUtente preferenzaUtente = preferenzaUtenteRepository.findByUtenteIdAndLibroId(utenteID, libroID).orElse(null);

        if (preferenzaUtente == null) {

            preferenzaUtenteRepository.like(utenteID, libroID);
            return true; // Like presente

        } else {

            preferenzaUtenteRepository.dislike(utenteID, libroID);
            return false; // Like non presente

        }


    }

}
