package br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.dto.VacinaDto;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.form.VacinaForm;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Vacina;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.repository.VacinaRepository;

@RestController
@RequestMapping("/vacinacoes")
public class VacinacaoController {

	@Autowired
	private VacinaRepository vacinaRepository;

	@GetMapping
	public Page<VacinaDto> listar(
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Vacina> vacinas = vacinaRepository.findAll(paginacao);
		return VacinaDto.converter(vacinas);

	}

	@GetMapping("/{usuario}")
	public ResponseEntity<VacinaDto> buscaVacinaUsuario(@PathVariable Usuario usuario) {
		Optional<Vacina> vacina = vacinaRepository.findByUsuario(usuario);
		if (vacina.isPresent()) {
			return ResponseEntity.ok(new VacinaDto(vacina.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<VacinaDto> cadastro(@Valid @RequestBody VacinaForm form, UriComponentsBuilder uriBuilder) {
		try {
			Vacina vacina = form.converter();
			vacinaRepository.save(vacina);

			URI uri = uriBuilder.path("/vacinacoes/{id}").buildAndExpand(vacina.getId()).toUri();
			return ResponseEntity.created(uri).body(new VacinaDto(vacina));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Vacina> vacina = vacinaRepository.findById(id);
		if (vacina.isPresent()) {
			vacinaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
