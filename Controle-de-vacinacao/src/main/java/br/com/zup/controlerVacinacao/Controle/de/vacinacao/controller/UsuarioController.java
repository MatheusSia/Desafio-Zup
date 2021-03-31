package br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller;

import java.net.URI;
import java.util.List;
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

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.dto.UsuarioDto;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.controller.form.UsuarioForm;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.repository.UsuarioRepository;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.repository.VacinaRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private VacinaRepository vacinaRepository;

	@GetMapping
	public Page<UsuarioDto> listar(
			@PageableDefault(sort = "dataNascimento", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return UsuarioDto.converter(usuarios);
	}

	@GetMapping("/comorbidade/{comorbidade}")
	public Page<UsuarioDto> listaComorbidade(@PathVariable String comorbidade,
			@PageableDefault(sort = "dataNascimento", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findByComorbidade(comorbidade, paginacao);
		return UsuarioDto.converter(usuarios);
	}
	
	@GetMapping("/duvidasfrequentes")
	public String duvidas() {
		return usuarioRepository.duvidas();
	}

	@GetMapping("/{nome}")
	public ResponseEntity<UsuarioDto> buscaUsuarioNome(@PathVariable String nome) {
		Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/contagem")
	public ResponseEntity<String> mediaVacinados() {
		return ResponseEntity
				.ok("A media dos vacidos é: " + usuarioRepository.porcentualVacinados(usuarioRepository, vacinaRepository) + "%");
	}
	
	@GetMapping("/naovacinados")
	public List<Usuario> naoVacinados() {
		return usuarioRepository.usuariosNaoVacinados();
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> cadastro(@Valid @RequestBody UsuarioForm form, UriComponentsBuilder uriBuilder) {
		List<Usuario> usuarios = usuarioRepository.findAll();
		for (Usuario usuario : usuarios) {
			if (form.getEmail().equals(usuario.getEmail()) || form.getCpf().equals(usuario.getCpf())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}