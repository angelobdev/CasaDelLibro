package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.payload.response.MessageResponse;
import com.angelobdev.casadellibro.service.CarrelliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrello")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class CarrelloRestController {

    @Autowired
    private CarrelliService carrelliService;

    @PostMapping("/crea/{utenteID}")
    public ResponseEntity<?> crea(@PathVariable Integer utenteID) {
        return ResponseEntity.ok(carrelliService.createCarrello(utenteID));
    }

    @PostMapping("/aggiungi/{carrelloID}/{libroID}")
    public ResponseEntity<?> aggiungi(@PathVariable Integer carrelloID, @PathVariable Integer libroID) {
        return ResponseEntity.ok(carrelliService.aggiungiLibro(carrelloID, libroID));
    }

    @PostMapping("/rimuovi/{carrelloID}/{libroID}")
    public ResponseEntity<?> rimuovi(@PathVariable Integer carrelloID, @PathVariable Integer libroID) {
        return ResponseEntity.ok(carrelliService.rimuoviLibro(carrelloID, libroID));
    }

    @PostMapping("/svuota/{carrelloID}")
    public ResponseEntity<?> svuota(@PathVariable Integer carrelloID) {
        return ResponseEntity.ok(carrelliService.svuotaCarrello(carrelloID));
    }

    @PostMapping("/acquista/{carrelloID}")
    public ResponseEntity<?> acquista(@PathVariable Integer carrelloID, @RequestParam Integer spedizioneID, @RequestParam Integer ritiroID) {
        return ResponseEntity.ok(carrelliService.acquistaCarrello(carrelloID, spedizioneID, ritiroID));
    }

    @GetMapping("/get/{utenteID}")
    public ResponseEntity<?> getCarelloUtente(@PathVariable Integer utenteID) {
        return ResponseEntity.ok(carrelliService.getCarelloUtente(utenteID));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllCarelli() {
        return ResponseEntity.ok(carrelliService.getAll());
    }

    @DeleteMapping("/delete/{carrelloID}")
    public ResponseEntity<?> deleteCarello(@PathVariable Integer carrelloID) {
        carrelliService.deleteCarello(carrelloID);
        return ResponseEntity.ok(new MessageResponse("Carrello eliminato con successo!"));
    }

}
