package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.Opcion;
import com.prestamo.entity.Rol;
import com.prestamo.entity.Usuario;
import com.prestamo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public List<Opcion> traerEnlacesDeUsuario(int idUsuario) {
		return repository.traerEnlacesDeUsuario(idUsuario);
	}

	@Override
	public List<Rol> traerRolesDeUsuario(int idUsuario) {
		return repository.traerRolesDeUsuario(idUsuario);
	}

	@Override
	public Usuario buscaPorLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public List<Usuario> listaJefePrestamistaTotales() {
		return repository.listaJefePrestamistaTotales();
	}

	@Override
	public List<Usuario> listaPrestamistaDeUnJefe(int idUsuario) {
		return repository.listaPrestamistaDeUnJefe(idUsuario);
	}

	@Override
	public List<Usuario> listaPrestamistariosDeUnPrestamista(int idUsuario) {
		return repository.listaPrestamistariosDeUnPrestamista(idUsuario);
	}

	@Override
	public Usuario registrarPrestatario(Usuario usuario) {
		return repository.save(usuario);
	}

	@Override
	public Usuario findByLogin(String login) {
		return repository.findByLogin(login);
	}

	@Override
	public Usuario findByDni(String dni) {
		return repository.findByDni(dni);
	}

	@Override
	public List<Usuario> listaUsuariosPrestatarios() {
		return repository.listaUsuariosPrestatarios();
	}

	@Override
	public List<Usuario> listaUsuariosPrestatariosXDNI(String dni) {
		return repository.listaUsuariosPrestatariosXDNI("%" + dni + "%");
	}

	@Override
	public void eliminaPrestatario(int idPrestatario) {
		repository.deleteById(idPrestatario);
	}

	@Override
	public Usuario buscarPorID(int id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Usuario> listaPrestatarioPorLoginIgualActualiza(String dni, int idUsuario) {
		return repository.listaUsuarioPorLoginIgualActualiza(dni, idUsuario);
	}

	@Override
	public List<Usuario> listaPrestatarioPorDniIgualActualiza(String dni, int idUsuario) {
		return repository.listaUsuarioPorDniIgualActualiza(dni, idUsuario);
	}
}
