package br.com.isea.centraldoacao.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.isea.centraldoacao.dto.request.BeneficiarioRequest;
import br.com.isea.centraldoacao.dto.response.BeneficiarioDTO;
import br.com.isea.centraldoacao.model.Beneficiario;
import br.com.isea.centraldoacao.model.Usuario;
import br.com.isea.centraldoacao.service.BeneficiarioService;
import br.com.isea.centraldoacao.service.UsuarioService;

@RestController
@RequestMapping("/api/beneficiarios")
public class BeneficiarioController {

	@Autowired
	private BeneficiarioService beneficiarioService;

	@Autowired
	private UsuarioService usuarioService;

	// Cadastrar Beneficiário
	@PostMapping
	public ResponseEntity<?> cadastrarBeneficiario(@RequestBody BeneficiarioRequest request) {
	    // Verifica se o usuário existe
	    Usuario usuario = usuarioService.buscarUsuarioPorId(request.getIdUsuario())
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	    // Verifica se o beneficiário já existe pelo CPF ou cadastroNoIsea
	    if (beneficiarioService.existeBeneficiario(request.getCpf(), request.getCadastroNoIsea())) {
	        return ResponseEntity.badRequest().body("Beneficiário com o mesmo CPF ou cadastro já existe.");
	    }

	    // Cria o novo beneficiário
	    Beneficiario beneficiario = new Beneficiario();
	    beneficiario.setUsuario(usuario);
	    beneficiario.setNome(request.getNome());
	    beneficiario.setDescricao(request.getDescricao());
	    beneficiario.setDataNascimento(LocalDate.parse(request.getDataNascimento()));
	    beneficiario.setCpf(request.getCpf());
	    beneficiario.setCadastroNoIsea(request.getCadastroNoIsea());
	    beneficiario.setNumeroChavePix(request.getNumeroChavePix());
	    beneficiario.setContato(request.getContato());
	    beneficiario.setEndereco(request.getEndereco());
	    beneficiario.setNomeResponsavel(request.getNomeResponsavel());

	    Beneficiario novoBeneficiario = beneficiarioService.cadastrarBeneficiario(beneficiario);
	    return ResponseEntity.ok(novoBeneficiario);
	}

	// Listar Beneficiários do Usuário
	 @GetMapping
	    public ResponseEntity<List<BeneficiarioDTO>> listarBeneficiarios(@RequestParam Long idUsuario) {
	        List<Beneficiario> beneficiarios = beneficiarioService.listarBeneficiariosPorUsuario(idUsuario);

	        if (beneficiarios.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
	        }

	        List<BeneficiarioDTO> beneficiariosDTO = beneficiarios.stream()
	                .map(beneficiario -> new BeneficiarioDTO(
	                        beneficiario.getNome(),
	                        beneficiario.getNomeResponsavel(),
	                        beneficiario.getCadastroNoIsea()
	                ))
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(beneficiariosDTO);
	    }

	// Atualizar Beneficiário
	@PutMapping("/{idBeneficiario}")
	public ResponseEntity<Beneficiario> atualizarBeneficiario(
	        @PathVariable Long idBeneficiario,
	        @RequestBody BeneficiarioRequest request) {

	    Beneficiario beneficiarioAtualizado = beneficiarioService.atualizarBeneficiario(
	            idBeneficiario,
	            request.getNome(),
	            request.getDescricao(),
	            request.getDataNascimento(),
	            request.getCpf(),
	            request.getCadastroNoIsea(),
	            request.getNumeroChavePix(),
	            request.getContato(),
	            request.getEndereco(),
	            request.getNomeResponsavel()
	    );
	    return ResponseEntity.ok(beneficiarioAtualizado);
	}


	// Excluir Beneficiário
	@DeleteMapping("/{idBeneficiario}")
	public ResponseEntity<Void> excluirBeneficiario(@PathVariable Long idBeneficiario) {
		beneficiarioService.excluirBeneficiario(idBeneficiario);
		return ResponseEntity.noContent().build();
	}
}
