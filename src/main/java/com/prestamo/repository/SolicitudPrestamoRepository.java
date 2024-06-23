package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.prestamo.entity.SolicitudPrestamo;

public interface SolicitudPrestamoRepository extends JpaRepository<SolicitudPrestamo, Integer>{
	

	@Query("select m from SolicitudPrestamo m where m.capital = ?1")
	public abstract List<SolicitudPrestamo> listaSolicitudPrestamoPorCapital(String idSolicitud);

}

























//validacion
/*
@Query("select e from SolicitudPrestamo e where e.descripcion = ?1")
public abstract List<SolicitudPrestamo> listaEjemploPorDescripcionIgual(String descripcion);

//crud
@Query("select e from SolicitudPrestamo e where e.descripcion like ?1")
public abstract List<SolicitudPrestamo> listaEjemploPorDescripcionLike(String descripcion);

@Query("select e from SolicitudPrestamo e where e.descripcion = ?1 and e.idEjemplo != ?2 ")
public abstract List<SolicitudPrestamo> listaEjemploPorDescripcionIgualActualiza(String descripcion, int idEjemplo);
*/