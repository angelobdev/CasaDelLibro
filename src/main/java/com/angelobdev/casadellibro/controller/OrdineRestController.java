package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.service.OrdineService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordine")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class OrdineRestController {

    @Autowired
    private OrdineService ordineService;

    // CRUD

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateOrdineDTO createOrdineDTO) {
        return ResponseEntity.ok(ordineService.create(createOrdineDTO.getCarrelloID(), createOrdineDTO.getSpedizioneID(), createOrdineDTO.getRitiroID()));
    }

    @GetMapping("/get/{ordineID}")
    public ResponseEntity<?> getById(@PathVariable Integer ordineID) {
        return ResponseEntity.ok(ordineService.getById(ordineID));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ordineService.getAll());
    }

    // DTOs

    @Data
    public static class CreateOrdineDTO {

        @NotBlank
        private Integer carrelloID;

        @NotBlank
        private Integer spedizioneID;

        @NotBlank
        private Integer ritiroID;

        @JsonCreator
        public CreateOrdineDTO(@JsonProperty("carrelloID") Integer carrelloID, @JsonProperty("spedizioneID") Integer spedizioneID, @JsonProperty("ritiroID") Integer ritiroID) {
            this.carrelloID = carrelloID;
            this.spedizioneID = spedizioneID;
            this.ritiroID = ritiroID;
        }
    }


}
