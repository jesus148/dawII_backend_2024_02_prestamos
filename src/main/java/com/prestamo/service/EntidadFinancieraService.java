package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.EntidadFinanciera;

public interface EntidadFinancieraService {
	
	//Validaciones 
	public abstract List<EntidadFinanciera> listaEntidadFinancieraPorNombreIgual(String nombre);
	public abstract List<EntidadFinanciera> listaEntidadFinancieraPorDescripcionIgualActualiza(String nombre, int idEntidadFinanciera);
	
	
	//CRUD
	public abstract EntidadFinanciera insertaActualizaEntidadFinanciera(EntidadFinanciera obj);
	public abstract List<EntidadFinanciera> listaEntidadFinancieraPorDescripcionLike(String nombre);
	public abstract void eliminaEntidadFinanciera(int idRevista);
	public abstract List<EntidadFinanciera> listaEntidadFinanciera();
	public abstract List<EntidadFinanciera> listarEntidadPorTipo(int id);

}
