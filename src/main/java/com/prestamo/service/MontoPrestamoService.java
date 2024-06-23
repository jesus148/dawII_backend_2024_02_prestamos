package com.prestamo.service;

import java.util.List;


import com.prestamo.entity.MontoPrestamo;





public interface MontoPrestamoService {


	

	
	public abstract MontoPrestamo registraMontoPrestamo(MontoPrestamo obj);
	
	public abstract List<MontoPrestamo> listaMontoPrestamoPorCapital(String capital);
	
	
	
	
	
	public abstract List<MontoPrestamo> findCapitalesByDias(int dias);
	public abstract List<MontoPrestamo> findMontosByCapital(int capital);
	
	
	
	
	
//	validaciones pa el modal actualizar
	public abstract List<MontoPrestamo> listaMontoPorCapitalIgualActualiza(String capital, int idMontoPrestamo);
	
	
	
	//Para el crud
	public abstract MontoPrestamo insertaActualizaMonto(MontoPrestamo obj);
	public abstract List<MontoPrestamo> listaMontoPorDescripcionLike(String capital);
	public abstract void eliminaMonto(int idMontoPrestamo);
	public abstract List<MontoPrestamo> listaMontoPrestamo();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
