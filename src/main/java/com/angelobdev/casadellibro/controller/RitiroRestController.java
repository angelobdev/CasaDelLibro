package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.service.RitiroService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/ritiro")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class RitiroRestController {

    @Autowired
    private RitiroService ritiroService;

    // CRUD

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody RitiroDTO ritiroDTO) throws ParseException {
        Date data = new SimpleDateFormat("yyyy-MM-dd").parse(ritiroDTO.getDataScelta());
        return ResponseEntity.ok(ritiroService.create(data));
    }

    // DTOs

    @Data
    public static class RitiroDTO {

        @NotBlank
        private String dataScelta;

        @JsonCreator
        public RitiroDTO(@JsonProperty("dataScelta") String dataScelta) {
            this.dataScelta = dataScelta;
        }
    }

}
