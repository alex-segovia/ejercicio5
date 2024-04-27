package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Entity.Opcion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OpcionRepository extends JpaRepository<Opcion, Integer> {


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update opcion set precio = ?1 where idopcion = ?2")
    void actualizarPrecio(float precio, int idOpcion);

    @Query(nativeQuery = true, value = "select * from opcion where idopcion = ?1")
    Opcion encontrarOpcionporId(int idOpcion);
}
