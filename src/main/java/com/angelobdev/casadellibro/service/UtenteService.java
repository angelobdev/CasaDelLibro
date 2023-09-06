package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.repository.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService implements UserDetailsService {

//    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private UtenteRepository utenteRepository;

    // CRUD

    public Utente getById(Integer id) {
        return utenteRepository.findById(id).orElse(null);
    }

    public Utente update(Integer id, Utente utente) {
        Utente toUpdate = utenteRepository.findById(id).orElse(null);
        assert toUpdate != null;

        toUpdate.setNome(utente.getNome());
        toUpdate.setCognome(utente.getCognome());
        toUpdate.setDataNascita(utente.getDataNascita());
        toUpdate.setUsername(utente.getUsername());
        toUpdate.setEmail(utente.getEmail());

        if (!utente.getPassword().startsWith("$2a")) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String newPassword = passwordEncoder.encode(utente.getPassword());
            toUpdate.setPassword(newPassword);
        }

        toUpdate.setAvatar(utente.getAvatar());

        return utenteRepository.save(toUpdate);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username).orElse(null);
        if (utente == null) throw new UsernameNotFoundException(username + " non trovato!");
        return utente;
    }

    // METHODS

    public String getAvatarFromUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByUsername(username).orElse(null);
        if (utente == null) throw new UsernameNotFoundException(username + " non trovato!");
        return utente.getAvatar();
    }
}
