package co.com.micha3lvega.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.micha3lvega.commons.entity.Usuario;
import co.com.micha3lvega.oauth.client.IUserClientFeing;

@Service
public class UserServices implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUserClientFeing iUserClientFeing;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = iUserClientFeing.findByUsername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("el usuario: " + username + " no fue encontrado");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> {
			return new SimpleGrantedAuthority(rol.getName());
		}).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	public Usuario findByUsername(String username) {
		return iUserClientFeing.findByUsername(username);
	}

}
