package com.prestamo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.MontoPrestamo;

import com.prestamo.repository.MontoRepository;


@Service
public class MontoPrestamoServiceImpl implements MontoPrestamoService{

	
	@Autowired
	private MontoRepository repository;
	
	
	@Override
	public List<MontoPrestamo> listaTodos(int idTipo) {
			return repository.listaMontoPrestamo(idTipo);

	}


	@Override
	public List<String> listaMonto(int id) {
		
		return repository.listaMonto(id);
	}




}
