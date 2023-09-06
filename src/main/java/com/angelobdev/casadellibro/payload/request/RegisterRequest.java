package com.angelobdev.casadellibro.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String cognome;

    @NotBlank
    private String dataNascita;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
