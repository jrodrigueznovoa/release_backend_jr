package cl.chile.ionix.test.tecnico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.chile.ionix.test.tecnico.model.UsersModelToken;
import cl.chile.ionix.test.tecnico.repository.IUserTokenRepository;

/**
 * The Class UsersTokensService.
 */
@Service
public class UsersTokensService implements UserDetailsService{
	

	/** The usr repository. */
	@Autowired
	private IUserTokenRepository usrRepository;
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersModelToken usuario = usrRepository.findOneByUserName(username);
		
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		usuario.getRols().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		UserDetails ud = new User(usuario.getUserName(), usuario.getPassword(), roles);
		return ud;
	}

}
