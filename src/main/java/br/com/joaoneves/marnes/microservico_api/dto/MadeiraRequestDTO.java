package br.com.joaoneves.marnes.microservico_api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class MadeiraRequestDTO {

    @NotBlank(message = "O tipo da madeira é obrigatório")
    private String tipo;

    @NotNull(message = "O fornecedor é obrigatório")
    private Long fornecedorId;

    @NotBlank(message = "O código de referência é obrigatório")
    private String codigoReferencia;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal precoMetroCubico;

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Long getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }

    public String getCodigoReferencia() { return codigoReferencia; }
    public void setCodigoReferencia(String codigoReferencia) { this.codigoReferencia = codigoReferencia; }

    public BigDecimal getPrecoMetroCubico() { return precoMetroCubico; }
    public void setPrecoMetroCubico(BigDecimal precoMetroCubico) { this.precoMetroCubico = precoMetroCubico; }
}
