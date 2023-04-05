package br.com.curso_spring;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.curso_spring.domain.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	public String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHora = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dataHora.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return Jwts
					.builder()
					.setSubject(usuario.getLogin())
					.setExpiration(data)
					.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
					.compact();
	}
	
	private Claims obterClaims(String token) {
		return Jwts
					.parser()
					.setSigningKey(chaveAssinatura)
					.parseClaimsJwt(token)
					.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			LocalDateTime data = claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(data);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException{
		return (String) obterClaims(token).getSubject();
	}
	
}
