package com.uniremigton.tarea.mvc.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniremigton.tarea.mvc.model.entity.Producto;
import com.uniremigton.tarea.mvc.model.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public void guardar(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> obtenerProducto(Integer id) {
		return productoRepository.findById(id);
	}

	@Override
	public void eliminar(Integer id) {
		productoRepository.deleteById(id);	
	}

}
