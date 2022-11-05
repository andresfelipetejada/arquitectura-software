package com.uniremigton.tarea.soa.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uniremigton.tarea.soa.persistence.entity.Producto;

public interface ProductoService {
	
	/**
	 * Guarda el producto
	 * 
	 * @param producto
	 */
	Producto guardar(Producto producto);
	
	/**
	 * Recupera la lista completa de productos
	 * 
	 * @return
	 */
	List<Producto> obtenerTodosProductos();
	
	/**
	 * Devuelve el producto correspondiente al id si existe
	 * 
	 * @param id Id del producto
	 * @return
	 */
	Optional<Producto> obtenerProducto(String id);
	
	/**
	 * Elimina el producto correspondiente al id
	 * 
	 * @param id Id del producto
	 */
	void eliminar(Integer id);

	Optional<Producto> actualizar(Producto producto);

	Page<Producto> findAll(Pageable pageable);

	Optional<Producto> obtenerPorId(Integer idProducto);
	
}
