package com.angelobdev.casadellibro.controller;

import com.angelobdev.casadellibro.service.LibriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/libri")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
public class LibroRestController {

    @Autowired
    private LibriService libriService;

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(libriService.getAllLibri());
    }

}
