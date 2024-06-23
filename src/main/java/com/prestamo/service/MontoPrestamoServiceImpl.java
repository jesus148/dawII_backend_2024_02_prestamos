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
	public List<MontoPrestamo> listaMontoPrestamoPorCapital(String capital) {
		return repository.listaMontoPrestamoPorCapital(capital);
	}

	
	

	
	
	

	@Override
	public List<MontoPrestamo> findCapitalesByDias(int dias) {

		return repository1.findCapitalesByDias(dias);
		
	}

	@Override
	public List<MontoPrestamo> findMontosByCapital(int capital) {

		
		return repository1.findMontosByCapital(capital);
	}







	
	
	
	
	
	
	
//	Crud
	
	
	@Override
	public MontoPrestamo insertaActualizaMonto(MontoPrestamo obj) {
		return repository.save(obj); 
	}

	
	@Override
	public List<MontoPrestamo> listaMontoPorDescripcionLike(String capital) {
		return repository.listaMontoPorDescripcionLike(capital);
	}
	
	
	@Override
	public void eliminaMonto(int idMontoPrestamo) {
		repository.deleteById(idMontoPrestamo);
		
	}

	@Override
	public List<MontoPrestamo> listaMontoPorCapitalIgualActualiza(String capital, int idMontoPrestamo) {
		return repository.listaMontoPorCapitalIgualActualiza(capital, idMontoPrestamo);
	}
	
	
	@Override
	public List<MontoPrestamo> listaMontoPrestamo() {
		return repository.findAll();
	}
	
	
	
	
	
	
	
	

}
