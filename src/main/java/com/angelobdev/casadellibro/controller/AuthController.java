package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.payload.request.LoginRequest;
import com.angelobdev.casadellibro.payload.request.RegisterRequest;
import com.angelobdev.casadellibro.payload.response.JwtResponse;
import com.angelobdev.casadellibro.payload.response.MessageResponse;
import com.angelobdev.casadellibro.repository.RuoloRepository;
import com.angelobdev.casadellibro.repository.UtenteRepository;
import com.angelobdev.casadellibro.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class AuthController {

//    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${com.angelobdev.casadellibro.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${com.angelobdev.casadellibro.jwtRememberExpirationMs}")
    private int jwtRememberExpirationMs;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Date expiration =
                loginRequest.getRemember() ?
                        new Date((new Date()).getTime() + jwtRememberExpirationMs) :
                        new Date((new Date()).getTime() + jwtExpirationMs);

        String jwt = jwtUtils.generateJwtToken(authentication, expiration);

        Utente utente = (Utente) authentication.getPrincipal();
        List<String> roles = utente.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        utente.getId(),
                        utente.getUsername(),
                        utente.getEmail(),
                        roles,
                        expiration
                )
        );

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) throws ParseException {

        // Username esiste già
        if (utenteRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Email esiste già
        if (utenteRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Creo un nuovo utente
        Utente utente = new Utente(
                registerRequest.getNome(),
                registerRequest.getCognome(),
                new SimpleDateFormat("yyyy-MM-dd").parse(registerRequest.getDataNascita()),
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()),
                ruoloRepository.findByNome("ROLE_USER").orElse(null)
        );

        utenteRepository.save(utente);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(utente.getUsername(), registerRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Date expiration = new Date((new Date()).getTime() + jwtExpirationMs);
        String jwt = jwtUtils.generateJwtToken(authentication, expiration);

        List<String> roles = utente.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        utente.getId(),
                        utente.getUsername(),
                        utente.getEmail(),
                        roles,
                        expiration
                )
        );
    }

}
