package com.prestamo.service;

import java.util.List;

import com.prestamo.entity.Cuenta;

public interface CuentaService {
	//PC1
	public abstract Cuenta insertaActualizaCuenta(Cuenta obj);
	public abstract List<Cuenta> validanumerodecuenta(String numero);

	
}
