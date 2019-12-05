package co.com.micha3lvega.oauth.services;

import co.com.micha3lvega.commons.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);

}