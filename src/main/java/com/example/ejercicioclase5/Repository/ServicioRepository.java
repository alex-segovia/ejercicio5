package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Dto.ServicioMascotaDto;
import com.example.ejercicioclase5.Dto.ServicioResponsableOpcionDto;
import com.example.ejercicioclase5.Entity.Servicio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Integer> {
    @Query(nativeQuery = true, value = "select m.nombre as nombre, Year(now())-m.anho as edad, s.hora_inicio as horaInicio, s.duracion as duracion, s.entrega as entrega, re.nombre as responsable, o.descripcion as servicio from opcion_servicio os inner join servicio s on os.servicio_idservicio = s.idservicio inner join opcion o on os.opcion_idopcion = o.idopcion inner join mascota m on s.mascota_idmascota = m.idmascota inner join responsable re on s.responsable_idresponsable = re.idresponsable where  m.idmascota=?1")
    List<ServicioMascotaDto> listarServiciosPorMascota(int idMascota);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from servicio where mascota_idmascota=?1")
    void borrarPorIdMascota(int idMascota);

    @Query(nativeQuery = true, value = "select * from servicio where mascota_idmascota=?1")
    List<Servicio> servicioPorIdMascota(int idMascota);


    @Query(nativeQuery = true, value = "SELECT s.idservicio, o.descripcion ,o.idopcion, r.idresponsable, o.precio, r.nombre  FROM (((sandylance.servicio s inner join opcion_servicio os on s.idservicio = os.servicio_idservicio) inner join opcion o on os.opcion_idopcion = o.idopcion) inner join responsable r on s.responsable_idresponsable = r.idresponsable)")
    List<ServicioResponsableOpcionDto> listarServicioResponsableOpcion();

    @Query(nativeQuery = true, value = "select * from servicio where idservicio = ?1")
    Optional<Servicio> encontrarServicioporId(int idServicio);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update servicio set responsable_idresponsable = ?2 where idservicio = ?1")
    void actualizarServicio(int idServicio, int idResponsable);



}
