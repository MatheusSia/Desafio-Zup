package br.com.zup.controlerVacinacao.Controle.de.vacinacao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByNome(String nome);

	Page<Usuario> findByComorbidade(String comorbidade, Pageable paginacao);

	default String duvidas() {
		String duvida = "1- Realizei meu cadastro, quando vou ser vacinado? Dependerá do seu grupo de prioridade.\r\n"
				+ "2- Quais doenças são classificadas como comorbidade? Anemia falciforme, Arritmias cardíacas, Cardiopatias congênita no adulto\r\n"
				+ "Cardiopatia hipertensiva, Cirrose hepática, Cor-pulmonale e Hipertensão pulmonar, Diabetes mellitus.\r\n"
				+ "3- É possível cadastrar um mesmo CPF mais de uma vez? Não. Após cadastrado, o cpf nao poderá ser usado de novo.\r\n"
				+ "4- Quem tomou a primeira dose precisa se cadastrar novamente? Não.\r\n"
				+ "5- Quais documentos devo apresentar na vacinação? Apenas o CPF.";
		return duvida;
	}

	default Double porcentualVacinados(UsuarioRepository usuarioRepository, VacinaRepository vacinaRepository) {
		Double totalUsuario = (double) usuarioRepository.count();
		Double totalVacina = (double) vacinaRepository.count();
		Double porcentualVacinados = (((totalUsuario + totalVacina) - totalUsuario) / totalUsuario) * 100;
		return Math.round(porcentualVacinados * 100) / 100d;
	}

	@Query("select u from Usuario u left join Vacina v on (u.id = v.usuario) where v.usuario is null")
	List<Usuario> usuariosNaoVacinados();
}
