package com.example.ejercicioclase5.Dto;

import java.time.LocalDateTime;

public interface ServicioMascotaDto {
    String getNombre();
    int getEdad();
    LocalDateTime getHoraInicio();
    int getDuracion();
    String getEntrega();
    String getResponsable();
    String getServicio();
}
