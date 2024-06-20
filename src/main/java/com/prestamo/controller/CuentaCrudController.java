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

import com.prestamo.entity.Cuenta;
import com.prestamo.entity.EntidadFinanciera;
import com.prestamo.service.CuentaService;
import com.prestamo.service.EntidadFinancieraService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudCuenta")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class CuentaCrudController {

	@Autowired
	private CuentaService cuentaService;
	
	@Autowired
	private EntidadFinancieraService entidadFinancieraService;
	
	@GetMapping("/listaEntidadPorTipo/{id}")
	@ResponseBody
	public List<EntidadFinanciera> listaEntidadPorTipo(@PathVariable("id")int id) {
		return entidadFinancieraService.listarEntidadPorTipo(id);
	}
	
	@GetMapping("/listaCuentaPorNumeroLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaEjemploPorNombreLike(@PathVariable("var") String numero){
		List<Cuenta> lstSalida = null;
		if (numero.equals("todos")) {
			lstSalida =cuentaService.listaCuenta();
		}else {
			lstSalida =cuentaService.listaCuentaPorNumeroLike(numero +  "%");
		}
		return ResponseEntity.ok(lstSalida);
	}
	
	@PostMapping("/registraCuenta")
	@ResponseBody
	public ResponseEntity<?> insertaCuenta(@RequestBody Cuenta objCuenta) {
		Map<String, Object> salida = new HashMap<>();
		try {
			objCuenta.setIdCuenta(0);
			objCuenta.setFechaRegistro(new Date());
			objCuenta.setFechaActualizacion(new Date());
			objCuenta.setEstado(AppSettings.ACTIVO);
			Cuenta objSalida = cuentaService.insertaActualizaCuenta(objCuenta);
			if (objSalida == null) {
				salida.put("mensaje",  AppSettings.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje",AppSettings.MENSAJE_REG_EXITOSO +"Cuenta con ID >>> " + objCuenta.getIdCuenta());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaCuenta")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCuenta(@RequestBody Cuenta objCuenta) {
		Map<String, Object> salida = new HashMap<>();
		try {
			
			objCuenta.setFechaActualizacion(new Date());

			Cuenta objSalida = cuentaService.insertaActualizaCuenta(objCuenta);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " Cuenta con ID ==> " + objCuenta.getIdCuenta() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@DeleteMapping("/eliminaCuenta/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaCuenta(@PathVariable("id") int idCuenta) {
		Map<String, Object> salida = new HashMap<>();
		try {
			cuentaService.eliminaCuenta(idCuenta);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Cuenta con ID ==> " + idCuenta + "." );
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@GetMapping("/validaNumero")
	public String validaDescripcion(@RequestParam(name = "numero")String numero) {
	List<Cuenta> lstSalida =cuentaService.validanumerodecuenta(numero);
		 if (lstSalida.isEmpty()) {
			 return "{\"valid\":true}";
		 }else {
			 return "{\"valid\":false}";
		 }
			
	}
	
	
}
