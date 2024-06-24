package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Opcion;
import com.prestamo.entity.Rol;
import com.prestamo.entity.Usuario;

public interface UsuarioService {

	public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario);

	public abstract List<Rol> traerRolesDeUsuario(int idUsuario);

	public abstract Usuario buscaPorLogin(String login);

	public abstract List<Usuario> listaJefePrestamistaTotales();

	public abstract List<Usuario> listaPrestamistaDeUnJefe(int idUsuario);

	public abstract List<Usuario> listaPrestamistariosDeUnPrestamista(int idUsuario);

	public abstract Usuario registrarPrestatario(Usuario usuario);

	Usuario findByLogin(String login);

	Usuario findByDni(String dni);

	Usuario buscarPorID(int id);

	List<Usuario> listaUsuariosPrestatarios();

	List<Usuario> listaUsuariosPrestatariosXDNI(String dni);

	public abstract void eliminaPrestatario(int idRevista);

	List<Usuario> listaPrestatarioPorDniIgualActualiza(String dni, int idUsuario);

	List<Usuario> listaPrestatarioPorLoginIgualActualiza(String dni, int idUsuario);
}
