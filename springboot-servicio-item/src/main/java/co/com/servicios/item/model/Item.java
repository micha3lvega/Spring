package co.com.servicios.item.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import co.com.micha3lvega.commons.entity.Producto;

@Getter
@Setter
@AllArgsConstructor
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private Producto producto;
	private Long cantidad;

	public Double calculeTotal() {
		return producto.getPrecio() * cantidad;
	}
}
