package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Grupo;

public interface GrupoService {
	
	public abstract Grupo insertaActualizaGrupo(Grupo obj);
	public abstract List<Grupo> listaGrupo();
	public abstract List<Grupo> listaGrupoPorDescripcionIgual(String descripcion);
	

}
