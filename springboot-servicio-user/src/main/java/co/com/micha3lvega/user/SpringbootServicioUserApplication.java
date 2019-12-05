package co.com.micha3lvega.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({ "co.com.micha3lvega.commons.entity" })//Configuracion para que el entity scan busque los entities cuando estan en otro proyecto
public class SpringbootServicioUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioUserApplication.class, args);
	}

}
