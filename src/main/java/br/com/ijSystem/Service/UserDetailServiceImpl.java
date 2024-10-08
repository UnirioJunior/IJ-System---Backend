package br.com.ijSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ijSystem.Entities.UserEntities;
import br.com.ijSystem.Repositories.UserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntities usuario = usuarioRepository.findByLogin(username).get();
		return UserDetailsImpl.build(usuario);
	}

}