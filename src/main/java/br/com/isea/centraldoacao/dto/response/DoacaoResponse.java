package br.com.isea.centraldoacao.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DoacaoResponse {
    private Long id;
    private Long idBeneficiario;
    private String descricaoPedido;
    private BigDecimal valor;
    private LocalDateTime dataPedido;
}
