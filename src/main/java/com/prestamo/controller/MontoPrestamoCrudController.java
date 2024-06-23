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


import com.prestamo.entity.MontoPrestamo;
import com.prestamo.service.MontoPrestamoService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudMontoPrestamo")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class MontoPrestamoCrudController {
	
	  @Autowired
	  private MontoPrestamoService montoPrestamoService; 
	  
	  
	  
	  
//	  METODO VALIDA AL REGISTRAR 
		@GetMapping("/validaMontoPorCapital")
		public String validaDescripcion(@RequestParam(name = "capital")String capital) {
			 List<MontoPrestamo> lstSalida =montoPrestamoService.listaMontoPrestamoPorCapital(capital);
			 if (lstSalida.isEmpty()) {
				 return "{\"valid\":true}";
			 }else {
				 return "{\"valid\":false}";
			 }
				
		}
	  
	  
	  
//	  METODO LSITAR POR PARAMETRO 
		@GetMapping("/listaMontoPorCapitalLike/{var}")
		@ResponseBody
		public ResponseEntity<?> listaMontoPorNombreLike(@PathVariable("var") String capital){
			List<MontoPrestamo> lstSalida = null;
			if (capital.equals("todos")) {
				lstSalida =montoPrestamoService.listaMontoPrestamo();
			}else {
				lstSalida =montoPrestamoService.listaMontoPorDescripcionLike(capital );
			}
			return ResponseEntity.ok(lstSalida);
		}
		
		
		
		
//		METODO REGISTRAR 
		@PostMapping("/registraMonto")
		@ResponseBody
		public ResponseEntity<?> insertaMonto(@RequestBody MontoPrestamo obj){
			HashMap<String, Object> salida = new HashMap<>();
			
			try {
				obj.setFechaRegistro(new Date());
				obj.setFechaActualizacion(new Date());
				obj.setEstado(AppSettings.ACTIVO);
				
				MontoPrestamo objSalida = montoPrestamoService.insertaActualizaMonto(obj);
				if (objSalida == null) {
					salida.put("mensaje", "Error en el registro");
				}else {
					salida.put("mensaje", "Registro de MontoPrestamo con el ID >>> " + obj.getIdMontoPrestamo() + 
												" >>> CAPITAL >> "+ obj.getCapital()+ 
												" DIAS : " + obj.getDias().getIdDataCatalogo() + 
												" MONTO A PAGAR : " + obj.getMonto());
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			}
			
			return ResponseEntity.ok(salida);
		}
		
		
		
		
		
		
//		METODO PA ACTUALIZAR 
		@PutMapping("/actualizaMonto")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> actualizaMontoPrestamo(@RequestBody MontoPrestamo obj) {
			Map<String, Object> salida = new HashMap<>();
			try {
				
				obj.setFechaActualizacion(new Date());

				MontoPrestamo objSalida = montoPrestamoService.insertaActualizaMonto(obj);
				if (objSalida == null) {
					salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
				} else {
					salida.put("mensaje", AppSettings.MENSAJE_ACT_EXITOSO + " monto de ID ==> " + obj.getIdMontoPrestamo() + ".");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		
		
		
		
		
//		METODO ELIMINAR POR ID
		@DeleteMapping("/eliminaMonto/{id}")
		@ResponseBody
		public ResponseEntity<Map<String, Object>> eliminaMonto(@PathVariable("id") int idMontoPrestamo) {
			Map<String, Object> salida = new HashMap<>();
			try {
				montoPrestamoService.eliminaMonto(idMontoPrestamo);
//				mostrara el id al eliminar  le envia al front
				salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Ejemplo de ID ==> " + idMontoPrestamo + ".");
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", AppSettings.MENSAJE_ELI_ERROR);
			}
			return ResponseEntity.ok(salida);
		}
		
		
		
		
		
		
		
//		METODO VALIDA AL HACER UPDATE en modal
//		parametros = en el front 
		@GetMapping("/validaCapitalActualiza")
		public String validaDescripcion(@RequestParam(name = "capital")String capital,
										@RequestParam(name = "idMontoPrestamo")int idMontoPrestamo) {
			 List<MontoPrestamo> lstSalida =montoPrestamoService.listaMontoPorCapitalIgualActualiza(capital, idMontoPrestamo);
//			 si esta vacia true listo pa update
			 if (lstSalida.isEmpty()) {
				 return "{\"valid\":true}";
			 }else {
				 return "{\"valid\":false}";
			}
				
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
