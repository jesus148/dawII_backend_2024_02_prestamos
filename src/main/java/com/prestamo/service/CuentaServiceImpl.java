package com.prestamo.service;

import java.util.List;

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

	@Override
	public List<Cuenta> validanumerodecuenta(String numero) {
		return repository.validanumerodecuenta(numero);
	}

	@Override
	public List<Cuenta> listaCuentaPorNumeroLike(String numero) {
		return repository.listaCuentaPorNumeroLike(numero);
	}

	@Override
	public void eliminaCuenta(int idCuenta) {
		repository.deleteById(idCuenta);
	}

	@Override
	public List<Cuenta> listaCuenta() {
		
		return repository.findAll();
	}
	
	
}
