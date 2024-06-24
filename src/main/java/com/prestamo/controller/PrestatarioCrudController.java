package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.prestamo.entity.Usuario;
import com.prestamo.entity.UsuarioHasRol;
import com.prestamo.entity.UsuarioHasRolPK;
import com.prestamo.repository.PrestatarioRepository;
import com.prestamo.service.UsuarioService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/crudPrestatario")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class PrestatarioCrudController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PrestatarioRepository prestatarioRepository;

	@GetMapping("/listaPrestatarioPorNombreLike/{var}")
	@ResponseBody
	public ResponseEntity<?> listaPrestatarioPorNombreLike(@PathVariable("var") String dni) {
		List<Usuario> lstSalida = null;
		if (dni.equals("todos")) {
			lstSalida = usuarioService.listaUsuariosPrestatarios();
		} else {
			lstSalida = usuarioService.listaUsuariosPrestatariosXDNI(dni);
		}
		return ResponseEntity.ok(lstSalida);
	}

	@PostMapping("/registraPrestatario")
	@ResponseBody
	public ResponseEntity<?> insertaPrestatario(@RequestBody Usuario obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			// Verificar si el login ya existe
			Usuario usuarioExistente = usuarioService.findByLogin(obj.getLogin());
			if (usuarioExistente != null) {
				salida.put("mensaje", "El login ya existe. Por favor, elige otro login.");
				return ResponseEntity.badRequest().body(salida);
			}
			Usuario usuarioExistente1 = usuarioService.findByDni(obj.getDni());
			if (usuarioExistente1 != null) {
				salida.put("mensaje", "El dni ya existe. Por favor, elige otro dni.");
				return ResponseEntity.badRequest().body(salida);
			}

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(obj.getLogin());
			obj.setPassword(encodedPassword);
			// Usuario en Sesion
			Usuario usuarioEnSesion = new Usuario();
			usuarioEnSesion.setIdUsuario(obj.getIdUsuario());
			obj.setUsuarioActualiza(usuarioEnSesion);
			obj.setUsuarioRegistro(usuarioEnSesion);
			obj.setUsuarioSuperior(usuarioEnSesion);

			obj.setFechaRegistro(new Date());
			obj.setFechaActualizacion(new Date());
			obj.setEstado(AppSettings.ACTIVO);

			// Asignar id = 0 al nuevo Usuario
			obj.setIdUsuario(0);
			Usuario objSalida = usuarioService.registrarPrestatario(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
			} else {
				salida.put("mensaje",
						AppSettings.MENSAJE_REG_EXITOSO + " Prestatario de ID ==> " + obj.getIdUsuario() + ".");
			}
			UsuarioHasRolPK obHasRolPK = new UsuarioHasRolPK();
			obHasRolPK.setIdRol(4);
			obHasRolPK.setIdUsuario(objSalida.getIdUsuario());
			UsuarioHasRol objHasRol = new UsuarioHasRol();
			objHasRol.setUsuarioHasRolPk(obHasRolPK);
			prestatarioRepository.save(objHasRol);

		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_REG_ERROR);
		}

		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaPrestatario")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaPrestatario(@RequestBody Usuario obj) {
		Map<String, Object> salida = new HashMap<>();
		try {

			Usuario usuarioActualiza = usuarioService.buscarPorID(obj.getEstado());

			Usuario prestatarioEncontrado = usuarioService.findByLogin(obj.getLogin());

			obj.setEstado(prestatarioEncontrado.getEstado());

			obj.setUsuarioRegistro(prestatarioEncontrado.getUsuarioRegistro());
			obj.setUsuarioSuperior(prestatarioEncontrado.getUsuarioSuperior());
			obj.setUsuarioActualiza(usuarioActualiza);
			obj.setFechaActualizacion(new Date());

			Usuario objSalida = usuarioService.registrarPrestatario(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje",
						AppSettings.MENSAJE_ACT_EXITOSO + " Prestatario de ID ==> " + obj.getIdUsuario() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaEstadoPrestatario")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaEstadoPrestatario(@RequestBody Usuario obj) {
		Map<String, Object> salida = new HashMap<>();
		try {

			Usuario usuarioActualiza = usuarioService.buscarPorID(obj.getEstado());

			Usuario prestatarioEncontrado = usuarioService.findByLogin(obj.getLogin());

			obj.setUsuarioRegistro(prestatarioEncontrado.getUsuarioRegistro());
			obj.setUsuarioSuperior(prestatarioEncontrado.getUsuarioSuperior());
			obj.setUsuarioActualiza(usuarioActualiza);

			obj.setFechaActualizacion(new Date());
			obj.setEstado(prestatarioEncontrado.getEstado() == 1 ? 0 : 1);
			Usuario objSalida = usuarioService.registrarPrestatario(obj);
			if (objSalida == null) {
				salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje",
						AppSettings.MENSAJE_ACT_EXITOSO + " Prestatario de ID ==> " + obj.getIdUsuario() + ".");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", AppSettings.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}

	@DeleteMapping("/eliminaPrestatario/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaPrestatario(@PathVariable("id") int idPrestatario) {
		Map<String, Object> salida = new HashMap<>();
		try {
			usuarioService.eliminaPrestatario(idPrestatario);
			salida.put("mensaje", AppSettings.MENSAJE_ELI_EXITOSO + " Prestatario de ID ==> " + idPrestatario + ".");
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje",
					AppSettings.MENSAJE_ELI_ERROR + " :: Esta relacionado en la tabla 'usuario_tiene_rol'");
		}
		return ResponseEntity.ok(salida);
	}

	@GetMapping("/validaDdniActualiza")
	public String validaDni(@RequestParam(name = "dni") String descripcion,
			@RequestParam(name = "idPrestatario") int idPrestatario) {
		List<Usuario> lstSalida = usuarioService.listaPrestatarioPorDniIgualActualiza(descripcion, idPrestatario);
		if (lstSalida.isEmpty()) {
			return "{\"valid\":true}";
		} else {
			return "{\"valid\":false}";
		}

	}

	@GetMapping("/validaLoginActualiza")
	public String validaLogin(@RequestParam(name = "login") String descripcion,
			@RequestParam(name = "idPrestatario") int idPrestatario) {
		List<Usuario> lstSalida = usuarioService.listaPrestatarioPorLoginIgualActualiza(descripcion, idPrestatario);
		if (lstSalida.isEmpty()) {
			return "{\"valid\":true}";
		} else {
			return "{\"valid\":false}";
		}

	}

}
