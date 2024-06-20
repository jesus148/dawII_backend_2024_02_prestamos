package com.prestamo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prestamo.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
	
	@Query("select c from Cuenta c where c.numero = ?1")
	public abstract List<Cuenta> validanumerodecuenta(String numero);


	
}
