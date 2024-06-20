package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Grupo;

public interface GrupoService {
	
	
		public abstract List<Grupo> listaGrupoPorDescripcionIgual(String descripcion);
	
	//PC2 CRUD
		public abstract Grupo insertaActualizaGrupo(Grupo obj);
		public abstract List<Grupo> listaGrupo();
		public abstract List<Grupo> listaGrupoPorDescripcionLike(String nombre);
		public abstract void eliminaGrupo(int idRevista);
		

}
