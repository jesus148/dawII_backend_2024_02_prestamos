package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamo.entity.MontoPrestamo;



public interface MontoRepository extends JpaRepository<MontoPrestamo, Integer>{
	
	@Query("Select e from  MontoPrestamo e where e.estado = ?1 ")
	public abstract List<MontoPrestamo> listaMontoPrestamo(int idTipo);
	

	
	
	@Query(value = "SELECT * FROM monto_prestamo  WHERE dias = :dias", nativeQuery = true)
    List<MontoPrestamo> findCapitalesByDias(@Param("dias") int dias);
 
 
 @Query(value = "SELECT monto FROM MontoPrestamo monto WHERE monto.capital = :capital")
    List<MontoPrestamo> findMontosByCapital(@Param("capital") int capital);
	
	
	@Query("select distinct x.monto from MontoPrestamo x where x.idMontoPrestamo = :var_dep  order by 1 asc")
	public abstract List<String> listaMonto(@Param("var_dep") int id);  
}
