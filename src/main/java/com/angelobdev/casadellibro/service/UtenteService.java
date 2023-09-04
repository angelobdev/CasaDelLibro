package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtenteService implements UserDetailsService {

    @Autowired
    private UtenteRepository utenteRepository;

    // CRUD

    public Utente getById(Integer id) {
        return utenteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username).orElse(null);
        if (utente == null)
            throw new UsernameNotFoundException(username + " non trovato!");
        return utente;
    }

    // METHODS

    public String getAvatarFromUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username).orElse(null);
        if (utente == null)
            throw new UsernameNotFoundException(username + " non trovato!");
        return utente.getAvatar();
    }
}
