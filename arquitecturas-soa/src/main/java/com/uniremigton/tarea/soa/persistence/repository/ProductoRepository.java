package com.uniremigton.tarea.soa.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniremigton.tarea.soa.persistence.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	Optional<Producto> findByCodigo(String codigo);

}
