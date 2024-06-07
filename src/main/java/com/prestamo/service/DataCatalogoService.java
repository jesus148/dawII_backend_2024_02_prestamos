package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.entity.Ejemplo;
import com.prestamo.entity.MontoPrestamo;

public interface DataCatalogoService {

	public abstract List<DataCatalogo> listaDataCatalogo(int idTipo);
	
	
	

}
