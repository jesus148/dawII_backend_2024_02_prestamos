package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.DataCatalogo;
import com.prestamo.entity.MontoPrestamo;

public interface DataCatalogoRepository extends JpaRepository<DataCatalogo, Integer>{
	
	
	@Query("Select r from DataCatalogo r where r.catalogo.idCatalogo =  ?1 order by descripcion asc")
	public abstract List<DataCatalogo> listaDataCatalogo(int idTipo);
	
	//public abstract MontoPrestamo RegistrarMontoPrestamo(MontoPrestamo obj);

	

}
