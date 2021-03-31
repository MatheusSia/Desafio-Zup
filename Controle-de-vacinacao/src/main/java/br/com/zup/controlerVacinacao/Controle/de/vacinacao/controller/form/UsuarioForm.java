package br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;

public class UsuarioForm {

	@NotNull
	@NotEmpty
	private String nome;

	@Email
	private String email;

	@NotNull
	@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
	private String cpf;

	@NotNull
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	private String dataNascimento;

	@NotNull
	private Boolean comorbidade;

	public Boolean getComorbidade() {
		return comorbidade;
	}

	public void setComorbidade(Boolean comorbidade) {
		this.comorbidade = comorbidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Usuario converter() {
		return new Usuario(nome, email, cpf, dataNascimento, comorbidade);
	}

}
