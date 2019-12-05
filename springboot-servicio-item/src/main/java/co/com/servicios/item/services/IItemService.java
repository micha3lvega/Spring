package co.com.servicios.item.services;

import java.util.List;

import co.com.servicios.item.model.Item;
import co.com.micha3lvega.commons.entity.Producto;

public interface IItemService {

	public List<Item> findAll();

	public Item findById(Long id, Long cantidad);

	public Producto save(Producto producto);

	public Producto update(Producto producto, Long id);

	public void delete(Long id);

}
