package br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.dto;

import org.springframework.data.domain.Page;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;

public class UsuarioDto {

	private String nome;
	private String email;
	private String cpf;
	private String dataNascimento;
	private Boolean comorbidade;

	public UsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.cpf = usuario.getCpf();
		this.dataNascimento = usuario.getDataNascimento();
		this.comorbidade = usuario.getComorbidade();
	}

	public Boolean getComorbidade() {
		return comorbidade;
	}

	public String getCpf() {
		return cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public static Page<UsuarioDto> converter(Page<Usuario> usuario) {
		return usuario.map(UsuarioDto::new);
	}

}
