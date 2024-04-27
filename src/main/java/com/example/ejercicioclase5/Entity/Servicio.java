package com.example.ejercicioclase5.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "servicio")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservicio", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mascota_idmascota", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "cuenta_idcuenta", nullable = false)
    private Cuenta cuenta;

    @Column(name = "hora_inicio", nullable = false)
    private LocalDateTime horaInicio;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Lob
    @Column(name = "entrega", nullable = false)
    private String entrega;

    @ManyToOne
    @JoinColumn(name = "responsable_idresponsable", nullable = false)
    private Responsable responsable;

}
