package br.com.curso_spring.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.curso_spring.domain.entity.Usuario;
import br.com.curso_spring.exception.SenhaInvalidaException;
import br.com.curso_spring.rest.dto.CredenciaisDTO;
import br.com.curso_spring.rest.dto.TokenDTO;
import br.com.curso_spring.service.impl.UsuarioServiceImpl;
import br.com.curso_spring.service.jwt.JwtService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl service;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return service.save(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			Usuario usuario = Usuario.builder()
									.login(credenciais.getLogin())
									.senha(credenciais.getSenha())
									.build();
			UserDetails usuarioAutenticado = service.autenticar(usuario);
			String token = jwtService.gerarToken(usuario);
			return new TokenDTO(usuario.getLogin(),token);
		}catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}
	
	@GetMapping
	public List<Usuario> listar(){
		return service.listar();
	}
	
}
