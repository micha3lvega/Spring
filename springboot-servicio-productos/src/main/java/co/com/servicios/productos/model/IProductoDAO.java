package co.com.servicios.productos.model;

import org.springframework.data.repository.CrudRepository;

import co.com.micha3lvega.commons.entity.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Long> {

}
