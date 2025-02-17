package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamo.entity.EntidadFinanciera;

public interface EntidadFinancieraRepository extends JpaRepository<EntidadFinanciera, Integer> {
	
	
	@Query("select e from EntidadFinanciera e where e.nombre = ?1")
	public abstract List<EntidadFinanciera> listaEjemploPorNombreIgual(String nombre);

	@Query("Select e from EntidadFinanciera e where e.tipoEntidad.idDataCatalogo = :id ")
	public abstract List<EntidadFinanciera> listaEntidadPorTipo(@Param("id") int id);
	
	@Query("Select e from EntidadFinanciera e where e.nombre like ?1")
	public abstract List<EntidadFinanciera> listaEntidadFinancieraPorDescripcionLike(String nombre);
	
	@Query("Select e from EntidadFinanciera e where e.nombre = ?1 and e.idEntidadFinanciera != ?2")
	public abstract List<EntidadFinanciera> listaEntidadFinancieraPorDescripcionIgualActualiza(String nombre, int idEntidadFinanciera);

}
