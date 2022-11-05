package com.uniremigton.tarea.soa.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.uniremigton.tarea.soa.persistence.entity.Producto;

public class ProductoPrecioUnidadesMinimoValidator
		implements ConstraintValidator<ProductoPrecioUnidadesMinimo, Producto> {

	@Override
	public boolean isValid(Producto producto, ConstraintValidatorContext context) {
		if (producto.getPrecio() == null || producto.getUnidadesMinimas() == null) {
			return false;
		}
		
		return producto.getPrecio() * producto.getUnidadesMinimas() > 10;
	}

}
