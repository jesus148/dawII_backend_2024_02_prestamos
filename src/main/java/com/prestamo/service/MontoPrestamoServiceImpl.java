package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.MontoPrestamo;
import com.prestamo.repository.MontoPrestamoRepository;
import com.prestamo.repository.MontoRepository;

@Service
public class MontoPrestamoServiceImpl implements MontoPrestamoService{
	
	@Autowired
	private MontoPrestamoRepository repository;
	
	
	@Autowired
	private MontoRepository repository1;
	

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
			return repository1.listaMontoPrestamo(idTipo);

	}


	@Override
	public List<String> listaMonto(int id) {
		
		return repository1.listaMonto(id);
	}

	

}
