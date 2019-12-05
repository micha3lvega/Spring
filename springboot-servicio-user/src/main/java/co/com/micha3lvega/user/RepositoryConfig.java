package co.com.micha3lvega.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import co.com.micha3lvega.commons.entity.Rol;
import co.com.micha3lvega.commons.entity.Usuario;

/**
 * https://docs.spring.io/spring-data/rest/docs/current/reference/html/
 * 
 * @author michael
 *
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

	/**
	 * Sobre escribir este metodo permite que el servicio tambien retorne los ids de
	 * los objetoss
	 */
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}

}
