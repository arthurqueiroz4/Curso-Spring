package br.com.curso_spring.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@NotEmpty(message = "Campo nome é obrigatório")
	@Column(name="nome", length=100)
	private String nome;
	
	@NotEmpty(message = "Campo CPF é obrigatório")
	@Column(name="cpf")
	@CPF(message = "Informe um CPF válido")
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private Set<Pedido> pedidos;
	
}
