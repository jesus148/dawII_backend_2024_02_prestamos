package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.prestamo.entity.MontoPrestamo;

public interface MontoPrestamoRepository extends JpaRepository<MontoPrestamo, Integer>{
	
	@Query("select m from MontoPrestamo m where m.capital = ?1")
	public abstract List<MontoPrestamo> listaMontoPrestamoPorCapital(String capital);
	
	
	
	
	
//	PARA EL CRUD
//	metodo lista ALL EN LA TABLA CON PARAMETRO
	@Query("select e from MontoPrestamo e where e.capital  = ?1")
	public abstract List<MontoPrestamo> listaMontoPorDescripcionLike(String capital);
	
	
//	Metodo validar al momento de actualizar en el modal con el id y la despcricion
//	 e.idMontoPrestamo != ?2" : tiene q salor diferente al valor q le estamos enviando osea q no concidere en la busqueda al mismo valor q le enviamos
	@Query("select e from MontoPrestamo e where e.capital = ?1 and e.idMontoPrestamo != ?2 ")
	public abstract List<MontoPrestamo> listaMontoPorCapitalIgualActualiza(String capital , int idMontoPrestamo);
	

}
