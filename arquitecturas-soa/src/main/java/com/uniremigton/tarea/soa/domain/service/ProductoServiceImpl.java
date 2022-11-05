package com.uniremigton.tarea.soa.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniremigton.tarea.soa.persistence.entity.Producto;
import com.uniremigton.tarea.soa.persistence.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Producto guardar(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Optional<Producto> actualizar(Producto producto) {
		
		if(productoRepository.existsById(producto.getId())) {
			producto = productoRepository.save(producto);
			return Optional.of(producto);
		}else {
			return Optional.empty();
		}		
	}
	
	@Override
	public List<Producto> obtenerTodosProductos() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> obtenerProducto(String codigo) {
		return productoRepository.findByCodigo(codigo);
	}

	@Override
	public void eliminar(Integer id) {
		productoRepository.deleteById(id);	
	}
	
	@Override
	public Page<Producto> findAll(final Pageable pageable) {
		
		return productoRepository.findAll(pageable);
	}
	
	@Override
	public Optional<Producto> obtenerPorId(Integer id) {
		return productoRepository.findById(id);
	}
	
}
