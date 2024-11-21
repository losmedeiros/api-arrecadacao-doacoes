package br.com.isea.centraldoacao.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.isea.centraldoacao.model.Beneficiario;
import br.com.isea.centraldoacao.model.Doacao;
import br.com.isea.centraldoacao.repository.BeneficiarioRepository;
import br.com.isea.centraldoacao.repository.DoacaoRepository;
//para Gerenciamento de Doações

@Service
public class DoacaoService {

	 @Autowired
	    private DoacaoRepository doacaoRepository;

	    @Autowired
	    private BeneficiarioRepository beneficiarioRepository;

	    public Doacao cadastrarDoacao(Long id_beneficiario, String descricao_pedido, BigDecimal valor) {
	        Beneficiario beneficiario = beneficiarioRepository.findById(id_beneficiario)
	                .orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));

	        Doacao doacao = new Doacao();
	        doacao.setBeneficiario(beneficiario);
	        doacao.setDescricaoPedido(descricao_pedido);
	        doacao.setValor(valor);

	        return doacaoRepository.save(doacao);
	    }

	    public List<Doacao> listarDoacoesPorUsuario(Long id_usuario) {
	        return doacaoRepository.findByBeneficiarioUsuarioId(id_usuario);
	    }

	    public Doacao atualizarDoacao(Long id_doacao, String descricao_pedido, BigDecimal valor) {
	        Doacao doacao = doacaoRepository.findById(id_doacao)
	                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

	        if (descricao_pedido != null) doacao.setDescricaoPedido(descricao_pedido);
	        if (valor != null) doacao.setValor(valor);

	        return doacaoRepository.save(doacao);
	    }

	    public void excluirDoacao(Long id_doacao) {
	        doacaoRepository.deleteById(id_doacao);
	    }
}