package co.com.servicios.productos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import co.com.micha3lvega.commons.entity.Producto;
import co.com.servicios.productos.model.services.IProductoServices;

@RestController
public class ProductoController {

	@Autowired
	private IProductoServices iProductoServices;

	@Value("${server.port}")
	private String port;

	@GetMapping
	public List<Producto> findAll() {
		return iProductoServices.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public Producto findById(@PathVariable Long id) {
		Producto producto = iProductoServices.findById(id);
		producto.setPort(port);
		return producto;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Producto save(@RequestBody Producto producto) {
		return iProductoServices.save(producto);

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = iProductoServices.findById(id);

		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());

		return iProductoServices.save(productoDb);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		iProductoServices.deleteById(id);
	}

}
