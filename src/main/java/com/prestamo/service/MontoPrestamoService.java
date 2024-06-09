package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.MontoPrestamo;

public interface MontoPrestamoService {
	
	public abstract MontoPrestamo registraMontoPrestamo(MontoPrestamo obj);
	public abstract List<MontoPrestamo> listaMontoPrestamo();
	public abstract List<MontoPrestamo> listaMontoPrestamoPorCapital(String capital);

}
