package com.example.inmaculada.api.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String contrasenaActual;
    private String contrasenaNueva;
    private String confirmaNuevaContrasena;

    public String getContrasenaActual() {
        return contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getConfirmaNuevaContrasena() {
        return confirmaNuevaContrasena;
    }

    public void setConfirmaNuevaContrasena(String confirmaNuevaContrasena) {
        this.confirmaNuevaContrasena = confirmaNuevaContrasena;
    }
}
