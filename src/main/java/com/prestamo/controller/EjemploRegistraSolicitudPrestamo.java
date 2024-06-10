package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.entity.SolicitudPrestamo;

import com.prestamo.service.SolicitudPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/solicitudPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class EjemploRegistraSolicitudPrestamo {


	
	@Autowired
	private SolicitudPrestamoService ejemploService;


	
	@PostMapping
	public ResponseEntity<?> registra(@RequestBody SolicitudPrestamo objSolicitud){
		HashMap<String, Object> salida = new HashMap<>();
		objSolicitud.setFechaRegistro(new Date());
		objSolicitud.setFechaActualizacion(new Date());
		objSolicitud.setEstado(AppSettings.ACTIVO);
		objSolicitud.setFechaFinPrestamo(new Date());
		objSolicitud.setFechaInicioPrestamo(new Date());
		
		
		
		DataCatalogo Obdatacatalago= new DataCatalogo();
		
		Obdatacatalago.setIdDataCatalogo(12);
		
		objSolicitud.setEstadoSolicitud(Obdatacatalago);
		
		
		

//	      Date date1 = new Date();  
//	      Calendar cal1 = Calendar.getInstance();  
//	      cal1.setTime(date1);  
//	      cal1.add(Calendar.DATE,objSolicitud.getDias() );  
		
		
		SolicitudPrestamo objSalida = ejemploService.insertaSolicitudPrestamo(objSolicitud);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de ejemplo con el ID >>> " + objSolicitud.getIdSolicitud() + 
										" >>> DES >> "+ objSolicitud.getUsuarioRegistro().getNombreCompleto());
		}
		return ResponseEntity.ok(salida);
	}


}










