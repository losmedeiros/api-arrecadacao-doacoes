package br.com.isea.centraldoacao.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DoacaoRequest {
    private Long idBeneficiario;
    private String descricaoPedido;
    private BigDecimal valor;
}

