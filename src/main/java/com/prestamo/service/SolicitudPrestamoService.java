package com.prestamo.service;


import java.util.List;


import com.prestamo.entity.SolicitudPrestamo;

public interface SolicitudPrestamoService {

	//Registra
	public abstract SolicitudPrestamo insertaSolicitudPrestamo(SolicitudPrestamo obj);
	
	//Para el crud
		public abstract SolicitudPrestamo insertaActualizaSolicitud(SolicitudPrestamo obj);
	    public abstract List<SolicitudPrestamo> listaSolicitudPrestamoPorCapital(String idSolicitud);
		public abstract void eliminaSolicitud(int idSolicitud);
		public abstract List<SolicitudPrestamo> listaSolicitud();

}
