package com.example.inmaculada.api.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String contrasenaActual;
    private String contrasenaNueva;
    private String confirmaNuevaContrasena;
}
