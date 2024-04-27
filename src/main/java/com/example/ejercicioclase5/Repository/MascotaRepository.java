package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Dto.MascotasServiciosDto;
import com.example.ejercicioclase5.Entity.Mascota;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    @Query(nativeQuery = true, value = "select m.idmascota as id, m.nombre as nombre, m.anho as anho, m.sexo as sexo, re.descripcion as raza, m.raza_otros as otraRaza, count(s.idservicio) as cantidadServicios from servicio s right join mascota m on m.idmascota = s.mascota_idmascota inner join raza_especie re on m.raza_especie_idraza = re.idraza group by m.idmascota")
    List<MascotasServiciosDto> obtenerMascotasServicios();

    @Query(nativeQuery = true, value = "select m.idmascota as id, m.nombre as nombre, m.anho as anho, m.sexo as sexo, re.descripcion as raza, m.raza_otros as otraRaza, count(s.idservicio) as cantidadServicios from servicio s right join mascota m on m.idmascota = s.mascota_idmascota inner join raza_especie re on m.raza_especie_idraza = re.idraza inner join cuenta c on m.cuenta_idcuenta = c.idcuenta where m.sexo like ?1 or re.descripcion like ?1 or m.raza_otros like ?1 or c.correo like ?1 group by m.idmascota")
    List<MascotasServiciosDto> obtenerMascotasServiciosBusqueda(String busqueda);

    List<Mascota> findByCuenta_Id(int idCuenta);


     @Modifying
     @Transactional
     @Query(nativeQuery = true, value = "UPDATE mascota\n" +
                "SET cuenta_idcuenta = NULL\n" +
                "WHERE idmascota =?1")
     void eliminarMascotaContacto (int idMascota);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update mascota set nombre=?1, anho=?2, sexo=?3, raza_especie_idraza=?4, raza_otros=?5 where idmascota=?6")
    void editarMascota(String nombre, String anho, String sexo, String razaEspecie, String razaOtros, int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into mascota (nombre,anho,historia,observaciones,sexo,raza_especie_idraza,raza_otros,cuenta_idcuenta) values(?1,?2,?3,?4,?5,?6,?7,?8)")
    void registrarMascota(String nombre, String anho, String historia, String observaciones, String sexo, String razaEspecie, String razaOtros, String cuenta);
}
