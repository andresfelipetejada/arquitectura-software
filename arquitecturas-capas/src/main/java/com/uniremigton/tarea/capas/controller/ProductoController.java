package com.uniremigton.tarea.capas.controller;

import java.util.Map;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uniremigton.tarea.capas.entity.Producto;
import com.uniremigton.tarea.capas.service.ProductoService;
import com.uniremigton.tarea.capas.validator.grupos.Crear;
import com.uniremigton.tarea.capas.validator.grupos.Editar;

@Controller
@RequestMapping("productos")
public class ProductoController {

	public static final String VISTA_LISTA = "lista";
	public static final String VISTA_FORMULARIO = "formulario";

	@Autowired
	private ProductoService productoService;

	@Autowired
	private MessageSource mensajes;

	@GetMapping(value = "/lista")
	public String listar(Model model) {
		model.addAttribute("titulo", mensajes.getMessage("aplicacion.nombre", null, LocaleContextHolder.getLocale()));
		model.addAttribute("productos", productoService.obtenerTodosProductos());

		return VISTA_LISTA;
	}

	@GetMapping(value = "/listaModelMap")
	public String listarModelMap(ModelMap model) {
		model.addAttribute("titulo", mensajes.getMessage("aplicacion.nombre", null, LocaleContextHolder.getLocale()));
		model.addAttribute("productos", productoService.obtenerTodosProductos());

		return VISTA_LISTA;
	}

	@GetMapping("/listaModelAndView")
	public ModelAndView listarModelAndView() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("titulo", mensajes.getMessage("aplicacion.nombre", null, LocaleContextHolder.getLocale()));
		mav.addObject("productos", productoService.obtenerTodosProductos());
		mav.setViewName(VISTA_LISTA);

		return mav;
	}

	@GetMapping("/crear")
	public String crear(Map<String, Object> model) {
		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", mensajes.getMessage("aplicacion.nombre", null, LocaleContextHolder.getLocale()));
		model.put("accion", "guardar");

		return VISTA_FORMULARIO;
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
		Producto producto = productoService.obtenerProducto(id).orElse(null);
		model.put("producto", producto);
		model.put("titulo", mensajes.getMessage("aplicacion.nombre", null, LocaleContextHolder.getLocale()));
		model.put("accion", "../actualizar");

		return producto != null ? VISTA_FORMULARIO : "redirect:../" + VISTA_LISTA;
	}

	@PostMapping("/guardar")
	public String guardar(@Validated({ Crear.class, Default.class }) Producto producto, BindingResult result) {
		if (result.hasErrors()) {
			return VISTA_FORMULARIO;
		}

		productoService.guardar(producto);

		return "redirect:" + VISTA_LISTA;
	}

	@PostMapping("/actualizar")
	public String actualizar(@Validated({ Editar.class, Default.class }) Producto producto, BindingResult result) {
		if (result.hasErrors()) {
			return VISTA_FORMULARIO;
		}

		productoService.guardar(producto);

		return "redirect:" + VISTA_LISTA;
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer idProducto) {
		productoService.eliminar(idProducto);

		return "redirect:../" + VISTA_LISTA;
	}

}
