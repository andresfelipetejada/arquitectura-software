package com.uniremigton.tarea.soa.api;

import java.util.List;
import java.util.Optional;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;

import com.uniremigton.tarea.soa.domain.service.ProductoService;
import com.uniremigton.tarea.soa.domain.validator.grupos.Crear;
import com.uniremigton.tarea.soa.domain.validator.grupos.Editar;
import com.uniremigton.tarea.soa.persistence.entity.Producto;

@RestController
@RequestMapping("producto")
public class ProductoController {

	@Autowired
	private ProductoService service;
		
	@GetMapping("/all")
	public List<Producto> obtenerTodosProductos() {
		return service.obtenerTodosProductos();
	}
	
	@GetMapping("/{id}")
	public Optional<Producto> consultarId(@PathVariable(value = "id") Integer idProducto) {
		return service.obtenerPorId(idProducto);
	}
	
	@GetMapping("/codigo/{codigo}")
	public Optional<Producto> consultarId(@PathVariable(value = "codigo") String codigo) {
		return service.obtenerProducto(codigo);
	}
	
	@PostMapping
	public Producto guardar(@Validated({ Crear.class, Default.class }) @RequestBody Producto producto, BindingResult result) {
		
		if (!result.hasErrors()) {
			return service.guardar(producto);
		}else {
			throw new RestClientResponseException(result.getAllErrors().toArray().toString(), 400, null, null, null, null);
		}
	}
	
	@PutMapping("/")
	public Optional<Producto> actualizar(@Validated({ Editar.class, Default.class }) @RequestBody Producto producto, BindingResult result) {
		if (!result.hasErrors()) {
			return service.actualizar(producto);
		}else {
			throw new RestClientResponseException(result.getAllErrors().toArray().toString(), 400, null, null, null, null);
		}
		
	}

	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable(value = "id") Integer idProducto) {
		service.eliminar(idProducto);
	}
}
