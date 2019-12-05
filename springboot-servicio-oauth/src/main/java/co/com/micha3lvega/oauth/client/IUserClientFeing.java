package co.com.micha3lvega.oauth.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.micha3lvega.commons.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface IUserClientFeing {

	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(@RequestParam String username);
	
	@GetMapping("/usuarios/search/obtenerPorUsername")
	public Usuario obtenerPorUsername(@RequestParam String username);

}
