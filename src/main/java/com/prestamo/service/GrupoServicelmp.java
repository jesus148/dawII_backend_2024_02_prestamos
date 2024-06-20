package com.prestamo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prestamo.entity.Grupo;
import com.prestamo.repository.GrupoRepository;

@Service
public class GrupoServicelmp implements GrupoService{

	@Autowired
	private GrupoRepository repository;
	
	@Override
	public Grupo insertaActualizaGrupo(Grupo obj) {
		return repository.save(obj);
	}

	@Override
	public List<Grupo> listaGrupo() {
		return repository.findAll();
	}

	@Override
	public List<Grupo> listaGrupoPorDescripcionIgual(String descripcion) {
		return repository.listaGrupoPorDescripcionIgual(descripcion);
	}

	@Override
	public List<Grupo> listaGrupoPorDescripcionLike(String descripcion) {
		
		return repository.listaGrupoPorDescripcionLike(descripcion);
	}

	@Override
	public void eliminaGrupo(int idGrupo) {
		repository.deleteById(idGrupo);
		
	}
	
}
