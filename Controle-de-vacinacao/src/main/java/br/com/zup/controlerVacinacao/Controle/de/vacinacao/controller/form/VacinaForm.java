package br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Vacina;

public class VacinaForm {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	private Usuario usuario;

	public String getNome() {
		return nome;
	}

	public void setNomeVacina(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Vacina converter() {
		return new Vacina(nome, usuario);
	}

}
