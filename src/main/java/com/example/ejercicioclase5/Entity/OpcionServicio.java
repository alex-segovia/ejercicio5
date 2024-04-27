package com.example.ejercicioclase5.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "opcion_servicio")
public class OpcionServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idopcion_servicio", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "opcion_idopcion", nullable = false)
    private Opcion opcion;

    @ManyToOne
    @JoinColumn(name = "servicio_idservicio", nullable = false)
    private Servicio servicio;

}
