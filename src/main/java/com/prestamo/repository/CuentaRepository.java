package com.prestamo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prestamo.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {


}
