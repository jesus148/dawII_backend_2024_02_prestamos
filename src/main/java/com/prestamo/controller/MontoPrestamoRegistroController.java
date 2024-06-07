package com.prestamo.controller;
//Ramos Ayasta

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.MontoPrestamo;
import com.prestamo.service.DataCatalogoService;
import com.prestamo.service.MontoPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/MontoPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class MontoPrestamoRegistroController {

  @Autowired
  private DataCatalogoService dataCatalogoService;
  

  @Autowired
  private MontoPrestamoService montoPrestamoService; 
  
  @GetMapping
	public ResponseEntity<List<MontoPrestamo>> lista(){
		List<MontoPrestamo> lstSalida = montoPrestamoService.listaMontoPrestamo();
		return ResponseEntity.ok(lstSalida);
	}
  
	@PostMapping
	public ResponseEntity<?> registra(@RequestBody MontoPrestamo objMPrestamo){
		HashMap<String, Object> salida = new HashMap<>();
		objMPrestamo.setFechaRegistro(new Date());
		objMPrestamo.setFechaActualizacion(new Date());
		objMPrestamo.setEstado(AppSettings.ACTIVO);
		
		MontoPrestamo objSalida = montoPrestamoService.registraMontoPrestamo(objMPrestamo);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de MontoPrestamo con el ID >>> " + objMPrestamo.getIdMontoPrestamo() + 
										" >>> CAPITAL >> "+ objMPrestamo.getCapital()+ 
										" DIAS : " + objMPrestamo.getDias() + 
										" MONTO A PAGAR : " + objMPrestamo.getMonto());
		}
		return ResponseEntity.ok(salida);
	}
}
