package com.prestamo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.Cuenta;
import com.prestamo.repository.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService{

	@Autowired 
	private CuentaRepository repository ;
	
	@Override
	public Cuenta insertaActualizaCuenta(Cuenta obj) {
		return repository.save(obj);
	}
	
	
}
