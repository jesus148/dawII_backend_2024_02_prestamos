package com.prestamo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.prestamo.entity.SolicitudPrestamo;

import com.prestamo.repository.SolicitudPrestamoRepository;

@Service
public class SolicitudPrestamoServiceImp implements SolicitudPrestamoService {

	@Autowired	
	private SolicitudPrestamoRepository repository;

	@Override
	public SolicitudPrestamo insertaSolicitudPrestamo(SolicitudPrestamo obj) {
		return repository.save(obj);

	}





	
}
