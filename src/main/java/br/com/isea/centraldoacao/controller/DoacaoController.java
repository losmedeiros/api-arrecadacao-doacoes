package br.com.isea.centraldoacao.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.isea.centraldoacao.model.Doacao;
import br.com.isea.centraldoacao.service.DoacaoService;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Cadastrar Pedido de Doação
    @PostMapping
    public ResponseEntity<Doacao> cadastrarDoacao(
            @RequestParam Long id_beneficiario,
            @RequestParam String descricao_pedido,
            @RequestParam(required = false) BigDecimal valor) {

        Doacao novaDoacao = doacaoService.cadastrarDoacao(id_beneficiario, descricao_pedido, valor);
        return ResponseEntity.ok(novaDoacao);
    }

    // Listar Pedidos de Doação
    @GetMapping
    public ResponseEntity<List<Doacao>> listarDoacoes(@RequestParam Long id_usuario) {
        List<Doacao> doacoes = doacaoService.listarDoacoesPorUsuario(id_usuario);
        return ResponseEntity.ok(doacoes);
    }

    // Atualizar Pedido de Doação
    @PutMapping("/{id_doacao}")
    public ResponseEntity<Doacao> atualizarDoacao(
            @PathVariable Long id_doacao,
            @RequestParam(required = false) String descricao_pedido,
            @RequestParam(required = false) BigDecimal valor) {

        Doacao doacaoAtualizada = doacaoService.atualizarDoacao(id_doacao, descricao_pedido, valor);
        return ResponseEntity.ok(doacaoAtualizada);
    }

    // Excluir Pedido de Doação
    @DeleteMapping("/{id_doacao}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id_doacao) {
        doacaoService.excluirDoacao(id_doacao);
        return ResponseEntity.noContent().build();
    }
}
