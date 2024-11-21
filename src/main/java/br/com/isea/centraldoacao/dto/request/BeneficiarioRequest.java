package br.com.isea.centraldoacao.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BeneficiarioRequest {

    private Long idUsuario;

    @NotBlank
    private String nome;

    private String descricao;

    private String dataNascimento;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    private String cpf;

    @NotBlank
    private String cadastroNoIsea;

    private String numeroChavePix;

    private String contato;

    private String endereco;

    private String nomeResponsavel;
}
