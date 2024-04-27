package com.example.ejercicioclase5.Repository;

import com.example.ejercicioclase5.Entity.RazaEspecie;
import com.example.ejercicioclase5.Entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsableRepository extends JpaRepository<Responsable,Integer>  {
}
