package br.com.isea.centraldoacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.isea.centraldoacao.dto.request.DoacaoRequest;
import br.com.isea.centraldoacao.dto.response.DoacaoResponse;
import br.com.isea.centraldoacao.model.Doacao;
import br.com.isea.centraldoacao.service.DoacaoService;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Cadastrar Pedido de Doação
    @PostMapping
    public ResponseEntity<DoacaoResponse> cadastrarDoacao(@RequestBody DoacaoRequest doacaoRequest) {
        Doacao novaDoacao = doacaoService.cadastrarDoacao(
            doacaoRequest.getIdBeneficiario(),
            doacaoRequest.getDescricaoPedido(),
            doacaoRequest.getValor()
        );
        return ResponseEntity.ok(convertToResponse(novaDoacao));
    }

    // Listar Pedidos de Doação
    @GetMapping
    public ResponseEntity<List<DoacaoResponse>> listarDoacoes(@RequestParam Long id_usuario) {
        List<Doacao> doacoes = doacaoService.listarDoacoesPorUsuario(id_usuario);
        return ResponseEntity.ok(doacoes.stream().map(this::convertToResponse).toList());
    }

    // Atualizar Pedido de Doação
    @PutMapping("/{id_doacao}")
    public ResponseEntity<DoacaoResponse> atualizarDoacao(
            @PathVariable Long id_doacao,
            @RequestBody DoacaoRequest doacaoRequest) {

        Doacao doacaoAtualizada = doacaoService.atualizarDoacao(
            id_doacao,
            doacaoRequest.getDescricaoPedido(),
            doacaoRequest.getValor()
        );
        return ResponseEntity.ok(convertToResponse(doacaoAtualizada));
    }

    // Excluir Pedido de Doação
    @DeleteMapping("/{id_doacao}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id_doacao) {
        doacaoService.excluirDoacao(id_doacao);
        return ResponseEntity.noContent().build();
    }

    // Método utilitário para converter Doacao em DoacaoResponse
    private DoacaoResponse convertToResponse(Doacao doacao) {
        DoacaoResponse response = new DoacaoResponse();
        response.setId(doacao.getId());
        response.setIdBeneficiario(doacao.getBeneficiario().getId());
        response.setDescricaoPedido(doacao.getDescricaoPedido());
        response.setValor(doacao.getValor());
        response.setDataPedido(doacao.getDataPedido());
        return response;
    }
}

