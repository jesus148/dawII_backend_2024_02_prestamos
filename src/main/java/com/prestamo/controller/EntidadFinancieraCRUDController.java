package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.EntidadFinanciera;
import com.prestamo.service.EntidadFinancieraService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudEntidadFinanciera")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class EntidadFinancieraCRUDController {
	
	@Autowired
	private EntidadFinancieraService entidadFinancieraService;
	
	@GetMapping("/listaEntidadFinancieraPorNombreLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaEjemploPorNombreLike(@PathVariable("var") String nombre){
		List<EntidadFinanciera> lstSalida = null;
		if(nombre.equals("todos")) {
			lstSalida = entidadFinancieraService.listaEntidadFinanciera();
		}else {
			lstSalida = entidadFinancieraService.listaEntidadFinancieraPorDescripcionLike(nombre + "%");
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	
	@PostMapping("/registraEntidadFinanciera")
	@ResponseBody
	public ResponseEntity<?> insertaEntidadFinanciera(@RequestBody EntidadFinanciera obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdEntidadFinanciera(0);
			obj.setFechaActualizacion(new Date());
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			EntidadFinanciera objSalida = entidadFinancieraService.insertaActualizaEntidadFinanciera(obj);
			if(objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + " Entidad Financiera ==> " + obj.getIdEntidadFinanciera() + ".");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
	@PutMapping("/actualizaEntidadFinanciera")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaEntidadFinanciera(@RequestBody EntidadFinanciera obj){
		Map<String, Object> salida = new HashMap<>();
		try {

			obj.setFechaActualizacion(new Date());
			
			EntidadFinanciera objSalida = entidadFinancieraService.insertaActualizaEntidadFinanciera(obj);
			if(objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			}else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Entidad Financiera ==> " + obj.getIdEntidadFinanciera() + ".");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
	@DeleteMapping("/eliminaEntidadFinanciera/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaEntidadFinanciera(@PathVariable("id") int idEntidadFinanciera){
		Map<String, Object> salida = new HashMap<>();
		try {
			entidadFinancieraService.eliminaEntidadFinanciera(idEntidadFinanciera);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Entidad Financiera ==> " + idEntidadFinanciera + ".");
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping("/validaNombreActualiza")
	public String validaNombre(@RequestParam(name = "nombre")String nombre,
								@RequestParam(name = "idEntidadFinanciera") int idEntidadFinanciera) {
		 List<EntidadFinanciera> lstSalida =entidadFinancieraService.listaEntidadFinancieraPorDescripcionIgualActualiza(nombre, idEntidadFinanciera);
		 if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		 }else {
			 return "{\"valid\":false}";
		 }
			
	}

}
