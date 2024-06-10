package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Grupo;
import com.prestamo.service.GrupoService;
import com.prestamo.util.AppSettings;

/*
 * Autor = Daniel Preciado Martinez 
 * */

@RestController
@RequestMapping("/url/grupo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class RegistroGrupoController {

@Autowired
private GrupoService grupoService;

@GetMapping
public ResponseEntity<List<Grupo>> lista(){
	List<Grupo> lstSalida = grupoService.listaGrupo();
	return ResponseEntity.ok(lstSalida);
}

@PostMapping
public ResponseEntity<?> registra(@RequestBody Grupo objGrupo){
    HashMap<String, Object> salida = new HashMap<>();
    objGrupo.setFechaRegistro(new Date());
    objGrupo.setFechaActualizacion(new Date());
    objGrupo.setEstado(AppSettings.ACTIVO);

    try {
        Grupo objSalida = grupoService.insertaActualizaGrupo(objGrupo);
        salida.put("mensaje", "Registro de ejemplo con el ID >>> " + objSalida.getIdGrupo() +
                                " >>> DES >>> " + objSalida.getDescripcion());
        return ResponseEntity.ok(salida);
    } catch (DataIntegrityViolationException e) {
        salida.put("mensaje", "Error en el registro: Ya existe un grupo con el mismo Jefe de Prestamista");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(salida);
    }
}


@GetMapping("/validaDescripcionRegistra")
public String validaDescripcion(@RequestParam(name = "descripcion")String descripcion) {
	List<Grupo> lstSalida = grupoService.listaGrupoPorDescripcionIgual(descripcion);
	if(lstSalida.isEmpty()) {
		return "{\"valid\":true}";
	 }else {
		 return "{\"valid\":false}";
	 }
	
}
	
	
}
