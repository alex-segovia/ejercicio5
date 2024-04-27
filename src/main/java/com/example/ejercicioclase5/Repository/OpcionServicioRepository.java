package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Entity.OpcionServicio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcionServicioRepository  extends JpaRepository<OpcionServicio,Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from opcion_servicio where servicio_idservicio=?1")
    void borrarPorIdServicio(int idServicio);
}
