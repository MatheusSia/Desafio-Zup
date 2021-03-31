package br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vacina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@ManyToOne
	@JoinColumn(nullable = false, name = "usuario_id")
	private Usuario usuario;

	private LocalDateTime dataVacina = LocalDateTime.now();

	public Vacina() {
	}

	public Vacina(String nome, Usuario usuario) {
		this.nome = nome;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataVacina() {
		return dataVacina;
	}

	public void setDataVacina(LocalDateTime dataVacina) {
		this.dataVacina = dataVacina;
	}

}
