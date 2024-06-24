package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prestamo.entity.Opcion;
import com.prestamo.entity.Rol;
import com.prestamo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("Select x from Usuario x where x.login = :#{#usu.login} and x.password = :#{#usu.password}")
	public abstract Usuario login(@Param(value = "usu") Usuario bean);

	@Query("Select p from Opcion p, RolHasOpcion pr, Rol r, UsuarioHasRol u where  p.idOpcion = pr.opcion.idOpcion and pr.rol.idRol = r.idRol and r.idRol = u.rol.idRol and u.usuario.idUsuario = ?1 ")
	public abstract List<Opcion> traerEnlacesDeUsuario(int idUsuario);

	@Query("Select r from Rol r, UsuarioHasRol u where r.idRol = u.rol.idRol and u.usuario.idUsuario = ?1 ")
	public abstract List<Rol> traerRolesDeUsuario(int idUsuario);

	public abstract Usuario findByLogin(String login);

	@Query("Select r from Usuario r, UsuarioHasRol u where r.idUsuario = u.usuario.idUsuario and u.rol.idRol = 2 order by r.apellidos desc ")
	public abstract List<Usuario> listaJefePrestamistaTotales();

	@Query("Select r from Usuario r, UsuarioHasRol u where r.idUsuario = u.usuario.idUsuario and u.rol.idRol = 3 and r.usuarioSuperior.idUsuario =?1 order by r.apellidos desc ")
	public abstract List<Usuario> listaPrestamistaDeUnJefe(int idUsuario);

	@Query("Select r from Usuario r, UsuarioHasRol u where r.idUsuario = u.usuario.idUsuario and u.rol.idRol = 4 and r.usuarioSuperior.idUsuario =?1 order by r.apellidos desc ")
	public abstract List<Usuario> listaPrestamistariosDeUnPrestamista(int idUsuario);

	public abstract Usuario findByDni(String dni);

	@Query("SELECT u.usuario FROM UsuarioHasRol u WHERE u.rol.idRol = 4")
	public abstract List<Usuario> listaUsuariosPrestatarios();

	@Query("SELECT u.usuario FROM  UsuarioHasRol u WHERE u.rol.idRol = 4 and u.usuario.dni like ?1 ")
	public abstract List<Usuario> listaUsuariosPrestatariosXDNI(String dni);

	@Query("select e from Usuario e where e.dni = ?1 and e.idUsuario != ?2 ")
	public abstract List<Usuario> listaUsuarioPorDniIgualActualiza(String dni, int idUsuario);

	@Query("select e from Usuario e where e.login = ?1 and e.idUsuario != ?2 ")
	public abstract List<Usuario> listaUsuarioPorLoginIgualActualiza(String login, int idUsuario);
}
