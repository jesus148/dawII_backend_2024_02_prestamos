package com.prestamo.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prestamo.entity.Usuario;
import com.prestamo.entity.UsuarioHasRol;
import com.prestamo.entity.UsuarioHasRolPK;
import com.prestamo.repository.PrestatarioRepository;
import com.prestamo.service.UsuarioService;
import com.prestamo.util.AppSettings;

@RestController
@RequestMapping("/url/prestatario")
@CrossOrigin(AppSettings.URL_CROSS_ORIGIN)
public class RegistroPrestatarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrestatarioRepository prestatarioRepository;

    @PostMapping
    public ResponseEntity<?> registra(@RequestBody Usuario objDatosPrestatario) {
        HashMap<String, Object> salida = new HashMap<>();
        // Verificar si el login ya existe
        Usuario usuarioExistente = usuarioService.findByLogin(objDatosPrestatario.getLogin());
        if (usuarioExistente != null) {
            salida.put("msg", "El login ya existe. Por favor, elige otro login.");
            return ResponseEntity.badRequest().body(salida);
        }
        Usuario usuarioExistente1 = usuarioService.findByDni(objDatosPrestatario.getDni());
        if (usuarioExistente1 != null) {
            salida.put("msg", "El dni ya existe. Por favor, elige otro dni.");
            return ResponseEntity.badRequest().body(salida);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(objDatosPrestatario.getLogin());
        objDatosPrestatario.setPassword(encodedPassword);
        // Usuario en Sesion
        Usuario usuarioEnSesion = new Usuario();
        usuarioEnSesion.setIdUsuario(objDatosPrestatario.getIdUsuario());
        objDatosPrestatario.setUsuarioActualiza(usuarioEnSesion);
        objDatosPrestatario.setUsuarioRegistro(usuarioEnSesion);
        objDatosPrestatario.setUsuarioSuperior(usuarioEnSesion);

        objDatosPrestatario.setFechaRegistro(new Date());
        objDatosPrestatario.setFechaActualizacion(new Date());
        objDatosPrestatario.setEstado(AppSettings.ACTIVO);

        // Asignar id = 0 al nuevo Usuario
        objDatosPrestatario.setIdUsuario(0);
        Usuario objSalida = usuarioService.registrarPrestatario(objDatosPrestatario);

        UsuarioHasRolPK obHasRolPK = new UsuarioHasRolPK();
        obHasRolPK.setIdRol(4);
        obHasRolPK.setIdUsuario(objSalida.getIdUsuario());
        UsuarioHasRol objHasRol = new UsuarioHasRol();
        objHasRol.setUsuarioHasRolPk(obHasRolPK);
        prestatarioRepository.save(objHasRol);

        if (objSalida == null) {
            salida.put("msg", "Error de registro zz");
        } else {
            salida.put("msg", "Se registro el prestatario ID: " + objDatosPrestatario.getIdUsuario() +
                    " Nombres: " + objDatosPrestatario.getNombreCompleto());
        }
        return ResponseEntity.ok(salida);
    }
}
