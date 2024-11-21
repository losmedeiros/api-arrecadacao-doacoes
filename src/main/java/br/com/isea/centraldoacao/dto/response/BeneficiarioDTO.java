package br.com.isea.centraldoacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiarioDTO {
    private String nome;
    private String nomeResponsavel;
    private String cadastroNoIsea;
}