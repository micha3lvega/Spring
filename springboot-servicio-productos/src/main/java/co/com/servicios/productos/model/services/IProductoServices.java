package co.com.servicios.productos.model.services;

import java.util.List;

import co.com.micha3lvega.commons.entity.Producto;

public interface IProductoServices {
	
	public List<Producto> findAll();

	public Producto findById(Long id);

	public Producto save(Producto producto);

	public void deleteById(Long id);

}
