package co.com.servicios.item.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import co.com.servicios.item.clients.IProductoClientRest;
import co.com.servicios.item.model.Item;
import co.com.micha3lvega.commons.entity.Producto;
import co.com.servicios.item.services.IItemService;

@Service
@Primary
public class ItemServicesFeing implements IItemService {

	@Autowired
	private IProductoClientRest iProductoClientRest;

	@Override
	public List<Item> findAll() {
		return iProductoClientRest.findAll().stream().map(p -> new Item(p, 1L)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Long cantidad) {
		return new Item(iProductoClientRest.findById(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return iProductoClientRest.save(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return iProductoClientRest.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		iProductoClientRest.delete(id);
	}

}
