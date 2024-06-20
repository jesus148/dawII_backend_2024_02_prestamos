package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.prestamo.entity.EntidadFinanciera;
import com.prestamo.repository.EntidadFinancieraRepository;

@Service
public class EntidadFinancieraServiceImp implements EntidadFinancieraService {

	@Autowired
	private EntidadFinancieraRepository repository;
	
	@Override
	public EntidadFinanciera insertaActualizaEntidadFinanciera(EntidadFinanciera obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}

	@Override
	public List<EntidadFinanciera> listaEntidadFinanciera() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<EntidadFinanciera> listaEntidadFinancieraPorNombreIgual(String nombre) {
		// TODO Auto-generated method stub
		return repository.listaEjemploPorNombreIgual(nombre);
	}

	@Override
	public List<EntidadFinanciera> listarEntidadPorTipo(int idTipo) {
		return repository.listaEntidadPorTipo(idTipo);
	}

	@Override
	public List<EntidadFinanciera> listaEntidadFinancieraPorDescripcionIgualActualiza(String nombre,
			int idEntidadFinanciera) {
		return repository.listaEntidadFinancieraPorDescripcionIgualActualiza(nombre, idEntidadFinanciera);
	}

	@Override
	public List<EntidadFinanciera> listaEntidadFinancieraPorDescripcionLike(String nombre) {
		return repository.listaEntidadFinancieraPorDescripcionLike(nombre);
	}

	@Override
	public void eliminaEntidadFinanciera(int idRevista) {
		repository.deleteById(idRevista);
		
	}
	
	
}
