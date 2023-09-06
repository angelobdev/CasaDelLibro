package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.service.SpedizioneService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spedizione")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class SpedizioneRestController {

    @Autowired
    private SpedizioneService spedizioneService;

    // CRUD

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody SpedizioneDTO spedizioneDTO) {
        return ResponseEntity.ok(spedizioneService.create(spedizioneDTO.getIndirizzo()));
    }

    // DTOs

    @Data
    public static class SpedizioneDTO {

        @NotBlank
        private String indirizzo;

        @JsonCreator
        public SpedizioneDTO(@JsonProperty("indirizzo") String indirizzo) {
            this.indirizzo = indirizzo;
        }
    }

}
