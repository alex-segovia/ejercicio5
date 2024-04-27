package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Entity.RazaEspecie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaEspecieRepository extends JpaRepository<RazaEspecie,Integer> {

}
