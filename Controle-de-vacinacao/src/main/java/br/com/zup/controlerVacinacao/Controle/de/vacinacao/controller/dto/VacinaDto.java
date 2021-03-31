package br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Vacina;

public class VacinaDto {

	private String nome;
	private Long id;
	private LocalDateTime dataVacina;

	public VacinaDto(Vacina vacina) {
		this.nome = vacina.getNome();
		this.id = vacina.getUsuario().getId();
		this.dataVacina = vacina.getDataVacina();

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public LocalDateTime getDataVacina() {
		return dataVacina;
	}

	public static Page<VacinaDto> converter(Page<Vacina> vacina) {
		return vacina.map(VacinaDto::new);
	}

}
