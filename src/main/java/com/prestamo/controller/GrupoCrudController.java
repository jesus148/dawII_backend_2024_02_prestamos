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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Grupo;
import com.prestamo.service.GrupoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudGrupo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class GrupoCrudController {

	
	@Autowired
	private GrupoService grupoService;
	
	
	@GetMapping("/listaGrupoPorDescripcionLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaGrupoPorDescripcionLike(@PathVariable("var") String descripcion){
		List<Grupo> lstSalida = null;
		if (descripcion.equals("todos")) {
			lstSalida =grupoService.listaGrupo();
		}else {
			lstSalida =grupoService.listaGrupoPorDescripcionLike(descripcion +  "%");
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping("/registraGrupo")
	@ResponseBody
	public ResponseEntity<?> insertaGrupo(@RequestBody Grupo objGrupo) {
		Map<String, Object> salida = new HashMap<>();
		try {
			objGrupo.setIdGrupo(0);
			objGrupo.setFechaActualizacion(new Date());
			objGrupo.setFechaRegistro(new Date());
			objGrupo.setEstado(AppSettings.ACTIVO);
			
			
			Grupo objSalida = grupoService.insertaActualizaGrupo(objGrupo);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_REG_EXITOSO + " ID de Grupo ==> " + objGrupo.getIdGrupo() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@PutMapping("/actualizaGrupo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaGrupo(@RequestBody Grupo objGrupo) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			objGrupo.setFechaActualizacion(new Date());
			objGrupo.setUsuarioActualiza(null);
			
			Grupo objSalida = grupoService.insertaActualizaGrupo(objGrupo);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " ID de Grupo ==> " + objGrupo.getIdGrupo() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminaGrupo/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaEjemplo(@PathVariable("id") int idGrupo) {
		Map<String, Object> salida = new HashMap<>();
		try {
			grupoService.eliminaGrupo(idGrupo);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO);
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
	
}
