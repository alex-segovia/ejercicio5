package com.example.ejercicioclase5.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmascota", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "anho", nullable = false, length = 45)
    private String anho;

    @Lob
    @Column(name = "historia", nullable = false)
    private String historia;

    @Lob
    @Column(name = "observaciones", nullable = false)
    private String observaciones;

    @Column(name = "sexo", nullable = false, length = 45)
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "raza_especie_idraza", nullable = false)
    private RazaEspecie razaEspecie;

    @Column(name = "raza_otros", length = 45)
    private String razaOtros;

    @ManyToOne
    @JoinColumn(name = "cuenta_idcuenta")
    private Cuenta cuenta;

}
