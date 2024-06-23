package com.prestamo.service;



import java.util.List;

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

	@Override
	public SolicitudPrestamo insertaActualizaSolicitud(SolicitudPrestamo obj) {
		return repository.save(obj);
	}
	
	@Override
	public void eliminaSolicitud(int idSolicitud) {
		 repository.deleteById(idSolicitud);
		
	}

	@Override
	public List<SolicitudPrestamo> listaSolicitud() {
		return repository.findAll();
	}

	@Override
	public List<SolicitudPrestamo> listaSolicitudPrestamoPorCapital(String idSolicitud) {
		return repository.listaSolicitudPrestamoPorCapital(idSolicitud);
	}


	





	
}
