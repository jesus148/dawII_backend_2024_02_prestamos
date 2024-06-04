package com.prestamo.service;

import java.util.List;


import com.prestamo.entity.EntidadFinanciera;

public interface EntidadFinancieraService {

	public abstract EntidadFinanciera insertaActualizaEntidadFinanciera(EntidadFinanciera obj);
	public abstract List<EntidadFinanciera> listaEntidadFinanciera();
	public abstract List<EntidadFinanciera> listaEntidadFinancieraPorNombreIgual(String nombre);
	
}
