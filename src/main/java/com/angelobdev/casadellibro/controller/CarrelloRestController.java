package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.payload.response.MessageResponse;
import com.angelobdev.casadellibro.service.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrello")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class CarrelloRestController {

    @Autowired
    private CarrelloService carrelloService;

    // CRUD

    @PostMapping("/create/{utenteID}")
    public ResponseEntity<?> create(@PathVariable Integer utenteID) {
        return ResponseEntity.ok(carrelloService.create(utenteID));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(carrelloService.getAll());
    }

    @GetMapping("/get/{utenteID}")
    public ResponseEntity<?> getByUtente(@PathVariable Integer utenteID) {
        return ResponseEntity.ok(carrelloService.getByUtenteId(utenteID));
    }

    @DeleteMapping("/delete/{carrelloID}")
    public ResponseEntity<?> delete(@PathVariable Integer carrelloID) {
        carrelloService.delete(carrelloID);
        return ResponseEntity.ok(new MessageResponse("Carrello eliminato con successo!"));
    }

    // METHODS

    @PostMapping("/aggiungi/{carrelloID}/{libroID}/{quantita}")
    public ResponseEntity<?> aggiungi(@PathVariable Integer carrelloID, @PathVariable Integer libroID, @PathVariable Integer quantita) {
        return ResponseEntity.ok(carrelloService.aggiungiLibro(carrelloID, libroID, quantita));
    }

    @PostMapping("/rimuovi/{carrelloID}/{libroID}/{quantita}")
    public ResponseEntity<?> rimuovi(@PathVariable Integer carrelloID, @PathVariable Integer libroID, @PathVariable Integer quantita) {
        return ResponseEntity.ok(carrelloService.rimuoviLibro(carrelloID, libroID, quantita));
    }

    @PostMapping("/svuota/{carrelloID}")
    public ResponseEntity<?> svuota(@PathVariable Integer carrelloID) {
        return ResponseEntity.ok(carrelloService.svuotaCarrello(carrelloID));
    }


}
