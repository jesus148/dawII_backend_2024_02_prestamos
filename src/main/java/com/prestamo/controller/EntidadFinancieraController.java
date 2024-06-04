package com.prestamo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.prestamo.entity.EntidadFinanciera;

import com.prestamo.service.EntidadFinancieraService;
import com.prestamo.util.AppSettings;

/**
 * 	@author BRANDON CORTEZ
 */
@RestController
@RequestMapping("/url/entidadFinanciera")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class EntidadFinancieraController {
	
	@Autowired
	private EntidadFinancieraService entidadFinancieraService;
	
	@GetMapping
	public ResponseEntity<List<EntidadFinanciera>> lista(){
		List<EntidadFinanciera> lstSalida = entidadFinancieraService.listaEntidadFinanciera();
		return ResponseEntity.ok(lstSalida);
	}

	@PostMapping
	public ResponseEntity<?> registra(@RequestBody EntidadFinanciera objEntidadFinanciera){
		HashMap<String, Object> salida = new HashMap<>();
		objEntidadFinanciera.setFechaRegistro(new Date());
		objEntidadFinanciera.setFechaActualizacion(new Date());
		objEntidadFinanciera.setEstado(AppSettings.ACTIVO);
		
		/*Usuario objUsuario = new Usuario();
		objUsuario.setIdUsuario(1);
		
		objEntidadFinanciera.setUsuarioRegistro(objUsuario);
		objEntidadFinanciera.setUsuarioActualiza(objUsuario);*/
		
		EntidadFinanciera objSalida = entidadFinancieraService.insertaActualizaEntidadFinanciera(objEntidadFinanciera);
		if (objSalida == null) {
			salida.put("mensaje", "Error en el registro");
		}else {
			salida.put("mensaje", "Registro de Entidad Financiera Exitoso ");
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/validaNombreRegistra")
	public String validaNombre(@RequestParam(name = "nombre")String nombre) {
		 List<EntidadFinanciera> lstSalida =entidadFinancieraService.listaEntidadFinancieraPorNombreIgual(nombre);
		 if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		 }else {
			 return "{\"valid\":false}";
		 }
			
	}
}