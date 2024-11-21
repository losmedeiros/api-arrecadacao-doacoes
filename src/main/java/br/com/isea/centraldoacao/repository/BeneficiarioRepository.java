package br.com.isea.centraldoacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.isea.centraldoacao.model.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
	List<Beneficiario> findByUsuarioId(Long usuarioId);
	boolean existsByCpf(String cpf);
	boolean existsByCadastroNoIsea(String cadastroNoIsea);

}
