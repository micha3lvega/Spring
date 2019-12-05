package co.com.servicios.item.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import co.com.servicios.item.model.Item;
import co.com.micha3lvega.commons.entity.Producto;
import co.com.servicios.item.services.IItemService;

@RestController
@RefreshScope // Anotacion que permite que el controllador tome los cambios de propiedad en
				// tiempo real es necesario spring-actuator
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private IItemService iItemService;

	@Value("${configuracion.texto}")
	private String texto;

	@Autowired
	private Environment env;

	@GetMapping
	public List<Item> findAll() {
		return iItemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "findByidBackup")
	@GetMapping("/{id}/{cantidad}")
	public Item findByid(@PathVariable Long id, @PathVariable Long cantidad) {
		return iItemService.findById(id, cantidad);
	}

	@GetMapping("/config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port) {

		Map<String, String> json = new HashMap<String, String>();

		json.put("texto", texto);
		json.put("port", port);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {

			json.put("profile.active", env.getActiveProfiles()[0]);
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	public Item findByidBackup(@PathVariable Long id, @PathVariable Long cantidad) {

		System.out.println("################## Entrada por el backup ##################");

		Producto producto = new Producto();
		producto.setCreateAt(new Date());
		producto.setId(id);
		producto.setNombre("Producto de respaldo");
		producto.setPort("9999");
		producto.setPrecio(99999.9);

		return new Item(producto, cantidad);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return iItemService.save(producto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		return iItemService.update(producto, id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		iItemService.delete(id);
	}
}
