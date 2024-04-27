package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Entity.Cuenta;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update cuenta set direccion=?1, password=?2  where idcuenta = ?3")
    void actualizarCuenta(String direccion, String password,int idCuenta);
}
