package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.Ejemplo;
import com.prestamo.entity.EntidadFinanciera;

public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Integer> {
	
	
	@Query("select e from EntidadFinanciera e where e.nombre = ?1")
	public abstract List<EntidadFinanciera> listaEjemploPorNombreIgual(String nombre);

}
