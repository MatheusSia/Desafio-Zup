package br.com.zup.controlerVacinacao.Controle.de.vacinacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Usuario;
import br.com.zup.controlerVacinacao.Controle.de.vacinacao.modelo.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

	Optional<Vacina> findByUsuario(Usuario usuario);

}
