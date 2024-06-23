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


import com.prestamo.entity.SolicitudPrestamo;
import com.prestamo.service.SolicitudPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudSolicitud")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class SolicitudPrestamoCrudController {

	@Autowired
	private SolicitudPrestamoService  solicitudService;
	
	
	@GetMapping("/listaSolicitudPorCapital/{var}")
	@ResponseBody
	public ResponseEntity<?> listaSolicitudPorCapitalLike(@PathVariable("var") String idSolicitud){
		List<SolicitudPrestamo> lstSalida = null;
		if (idSolicitud.equals("todos")) {
			lstSalida =solicitudService.listaSolicitud();
		}else {
			lstSalida =solicitudService.listaSolicitudPrestamoPorCapital(idSolicitud +  "%");
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping("/registraSolicitud")
	@ResponseBody
	public ResponseEntity<?> insertaSolicitud(@RequestBody SolicitudPrestamo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdSolicitud(0);
			obj.setFechaActualizacion(new Date());
			obj.setFechaRegistro(new Date());
			obj.setEstado(AppSettings.ACTIVO);
			
			SolicitudPrestamo objSalida = solicitudService.insertaActualizaSolicitud(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + " Solicitud de ID ==> " + obj.getIdSolicitud() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaSolicitud")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaSolicitud(@RequestBody SolicitudPrestamo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			obj.setFechaActualizacion(new Date());

			SolicitudPrestamo objSalida = solicitudService.insertaActualizaSolicitud(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Solicitud de ID ==> " + obj.getIdSolicitud() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@DeleteMapping("/eliminaSolicitud/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaSolicitud(@PathVariable("id") int idSolicitud) {
		Map<String, Object> salida = new HashMap<>();
		try {
			solicitudService.eliminaSolicitud(idSolicitud);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Solicitud de ID ==> " + idSolicitud + "." );
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	/*@GetMapping("/validaDescripcionActualiza")
	public String validaDescripcion(@RequestParam(name = "descripcion")String descripcion,
									@RequestParam(name = "idSolicitud")int idSolicitud) {
		 List<SolicitudPrestamo> lstSalida = SolicitudService.listaSolicitudPorDescripcionIgualActualiza(descripcion, idSolicitud);
		 if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		 }else {
			 return "{\"valid\":false}";
		 }
			
	}*/
	
	
}