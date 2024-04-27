package com.example.ejercicioclase5.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcuenta", nullable = false)
    private Integer id;

    @Column(name = "correo", length = 45)
    private String correo;

    @Column(name = "direccion", nullable = false, length = 80)
    private String direccion;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "telefono", nullable = false, length = 10)
    private String telefono;

    @Column(name = "admin")
    private Integer admin;

}
