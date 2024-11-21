package br.com.isea.centraldoacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.isea.centraldoacao.model.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findByBeneficiarioUsuarioId(Long usuarioId);

}
