package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.MontoPrestamo;




public interface MontoPrestamoService {

	public abstract List<MontoPrestamo> listaTodos(int idTipo);
	

	
	public abstract List<String> listaMonto(int id);
	

}
