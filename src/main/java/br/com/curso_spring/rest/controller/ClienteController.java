package br.com.curso_spring.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

	@RequestMapping(
			value = "/hello/{nome}",
			method = RequestMethod.GET,
			consumes = {"applicantion/json", "application/xml"},//recebe
			produces = {"applicantion/json", "application/xml"}//entrega
		)
	@ResponseBody
	public String helloClientes(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello ,%s", nomeCliente);
	}
	
	
	//OBTER DADOS DO FRONT -> GET
	
}
