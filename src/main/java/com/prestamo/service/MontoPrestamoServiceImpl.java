package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.MontoPrestamo;
import com.prestamo.repository.MontoPrestamoRepository;

@Service
public class MontoPrestamoServiceImpl implements MontoPrestamoService{
	
	@Autowired
	private MontoPrestamoRepository repository;

	@Override
	public MontoPrestamo registraMontoPrestamo(MontoPrestamo obj) {
		return repository.save(obj);
	}

	@Override
	public List<MontoPrestamo> listaMontoPrestamo() {
		return repository.findAll();
	}

	@Override
	public List<MontoPrestamo> listaMontoPrestamoPorCapital(String capital) {
		return repository.listaMontoPrestamoPorCapital(capital);
	}

	
	
	@Override
	public List<MontoPrestamo> listaTodos(int idTipo) {
			return repository.listaMontoPrestamo(idTipo);

	}


	@Override
	public List<String> listaMonto(int id) {
		
		return repository.listaMonto(id);
	}

	

}
