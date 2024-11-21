package br.com.isea.centraldoacao.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.isea.centraldoacao.model.Beneficiario;
import br.com.isea.centraldoacao.repository.BeneficiarioRepository;
//para Gerenciamento de Beneficiários

@Service
public class BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	public boolean existeBeneficiario(String cpf, String cadastroNoIsea) {
		return beneficiarioRepository.existsByCpf(cpf) || beneficiarioRepository.existsByCadastroNoIsea(cadastroNoIsea);
	}

	public Beneficiario cadastrarBeneficiario(Beneficiario beneficiario) {
		return beneficiarioRepository.save(beneficiario);
	}

	public List<Beneficiario> listarBeneficiariosPorUsuario(Long id_usuario) {
		return beneficiarioRepository.findByUsuarioId(id_usuario);
	}

	public Beneficiario atualizarBeneficiario(Long id_beneficiario, String nome, String descricao,
			String dataNascimento, String cpf, String cadastroNoIsea, String numeroChavePix, String contato,
			String endereco, String nomeResponsavel) {
		Beneficiario beneficiario = beneficiarioRepository.findById(id_beneficiario)
				.orElseThrow(() -> new RuntimeException("Beneficiário não encontrado"));

		if (nome != null)
			beneficiario.setNome(nome);
		if (descricao != null)
			beneficiario.setDescricao(descricao);
		if (dataNascimento != null)
			beneficiario.setDataNascimento(LocalDate.parse(dataNascimento));
		if (cpf != null)
			beneficiario.setCpf(cpf);
		if (cadastroNoIsea != null)
			beneficiario.setCadastroNoIsea(cadastroNoIsea);
		if (numeroChavePix != null)
			beneficiario.setNumeroChavePix(numeroChavePix);
		if (contato != null)
			beneficiario.setContato(contato);
		if (endereco != null)
			beneficiario.setEndereco(endereco);
		if (nomeResponsavel != null)
			beneficiario.setNomeResponsavel(nomeResponsavel);

		return beneficiarioRepository.save(beneficiario);
	}

	public void excluirBeneficiario(Long id_beneficiario) {
		beneficiarioRepository.deleteById(id_beneficiario);
	}
}
