package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.payload.response.MessageResponse;
import com.angelobdev.casadellibro.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utente")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true", allowedHeaders = {"Authorization"})
public class UtenteRestController {

    @Autowired
    private UtenteService utenteService;

    // METHODS

    @GetMapping("/get/avatar/{username}")
    public ResponseEntity<?> getAvatar(@PathVariable String username) {
        return ResponseEntity.ok(new MessageResponse(utenteService.getAvatarFromUsername(username)));
    }

}
