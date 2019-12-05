package co.com.servicios.item.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.com.servicios.item.model.Item;
import co.com.micha3lvega.commons.entity.Producto;
import co.com.servicios.item.services.IItemService;

@Service
//@Primary
public class ItemServices implements IItemService {

	@Autowired
	private RestTemplate client;

	@Override
	public List<Item> findAll() {
		List<Producto> productos = Arrays
				.asList(client.getForObject("http://servicio-productos/listar", Producto[].class));

		return productos.stream().map(p -> new Item(p, 1L)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Long cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = client.getForObject("http://servicio-productos/{id}", Producto.class, pathVariables);
		return new Item(producto, cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);

		ResponseEntity<Producto> response = client.exchange("http://servicio-productos", HttpMethod.POST, body,
				Producto.class);
		Producto productoResponse = response.getBody();
		return productoResponse;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());

		HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = client.exchange("http://servicio-productos/{id}", HttpMethod.PUT, body,
				Producto.class, pathVariables);

		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		client.delete("http://servicio-productos/{id}", pathVariables);

	}

}
