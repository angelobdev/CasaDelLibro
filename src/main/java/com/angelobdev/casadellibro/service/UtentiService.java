package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.repository.UtentiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtentiService implements UserDetailsService {

    @Autowired
    private UtentiRepository utentiRepository;

    public Utente getById(Integer id) {
        return utentiRepository.findById(id).orElse(null);
    }

    public String getAvatarFromUsername(String username) throws UsernameNotFoundException {
        Utente utente = utentiRepository.findByUsername(username).orElse(null);
        if (utente == null)
            throw new UsernameNotFoundException(username + " non trovato!");
        return utente.getAvatar();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utentiRepository.findByUsername(username).orElse(null);
        if (utente == null)
            throw new UsernameNotFoundException(username + " non trovato!");
        return utente;
    }
}
