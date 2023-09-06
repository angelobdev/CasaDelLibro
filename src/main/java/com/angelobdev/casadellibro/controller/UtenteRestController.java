package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.model.Utente;
import com.angelobdev.casadellibro.payload.response.MessageResponse;
import com.angelobdev.casadellibro.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class UtenteRestController {

    @Autowired
    private UtenteService utenteService;

    // CRUD

    @GetMapping("/get/{utenteID}")
    public ResponseEntity<?> get(@PathVariable Integer utenteID) {
        return ResponseEntity.ok(utenteService.getById(utenteID));
    }

    @PostMapping("/update/{utenteID}")
    public ResponseEntity<?> update(@PathVariable Integer utenteID, @RequestBody Utente utente) {
        return ResponseEntity.ok(utenteService.update(utenteID, utente));
    }

    // METHODS

    @GetMapping("/get/avatar/{username}")
    public ResponseEntity<?> getAvatar(@PathVariable String username) {
        return ResponseEntity.ok(new MessageResponse(utenteService.getAvatarFromUsername(username)));
    }

}
