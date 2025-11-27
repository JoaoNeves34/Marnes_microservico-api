package br.com.joaoneves.marnes.microservico_api.dto;

import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import java.math.BigDecimal;

public class MadeiraResponseDTO {
    private Long id;
    private String tipo;
    private Long fornecedorId;
    private String fornecedorNome;
    private BigDecimal precoMetroCubico;

    public MadeiraResponseDTO(Madeira madeira) {
        this.id = madeira.getId();
        this.tipo = madeira.getTipo();
        if (madeira.getFornecedor() != null) {
            this.fornecedorId = madeira.getFornecedor().getId();
            this.fornecedorNome = madeira.getFornecedor().getNome();
        }
        this.precoMetroCubico = madeira.getPrecoMetroCubico();
    }

    public Long getId() { return id; }
    public String getTipo() { return tipo; }
    public Long getFornecedorId() { return fornecedorId; }
    public String getFornecedorNome() { return fornecedorNome; }
    public BigDecimal getPrecoMetroCubico() { return precoMetroCubico; }
}
