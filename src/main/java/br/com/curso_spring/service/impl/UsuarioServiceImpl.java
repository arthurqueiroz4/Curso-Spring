package br.com.curso_spring.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;
import br.com.curso_spring.domain.entity.Usuario;
import br.com.curso_spring.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService{
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository
								.findByLogin(username)
								.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado")); 
		String[] roles = usuario.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return  User
					.builder()
					.username(usuario.getLogin())
					.password(usuario.getSenha())
					.roles(roles)
					.build();
	}

}
