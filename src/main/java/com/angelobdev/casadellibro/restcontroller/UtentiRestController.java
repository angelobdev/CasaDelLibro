package com.angelobdev.casadellibro.restcontroller;

import com.angelobdev.casadellibro.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/utenti")
public class UtentiRestController {

    @Autowired
    private UtentiService utentiService;

    // CREATE (Disabilitato - Auth)

    // READ

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUtente(@PathVariable Integer id) {
        return ResponseEntity.ok(utentiService.getUtente(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getUtente() {
        return ResponseEntity.ok(utentiService.getAllUtenti());
    }

    // UPDATE

    // DELETE

}
