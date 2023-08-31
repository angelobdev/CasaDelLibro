package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.payload.response.MessageResponse;
import com.angelobdev.casadellibro.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true", allowedHeaders = {"Authorization"})
public class UtenteRestController {

    @Autowired
    private UtentiService utentiService;

    @GetMapping("/avatar/{username}")
    public ResponseEntity<?> getAvatar(@PathVariable String username){
        return ResponseEntity.ok(new MessageResponse(utentiService.getAvatarFromUsername(username)));
    }

}
