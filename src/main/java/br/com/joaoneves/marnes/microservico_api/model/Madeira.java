package br.com.joaoneves.marnes.microservico_api.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_madeiras")
public class Madeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo;

    // now model references Fornecedor entity instead of plain origem string
    @ManyToOne(optional = false)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(nullable = false, unique = true)
    private String codigoReferencia;

    @Column(nullable = false)
    private BigDecimal precoMetroCubico;

    public Madeira() {}

    public Madeira(Long id, String tipo, Fornecedor fornecedor, String codigoReferencia, BigDecimal precoMetroCubico) {
        this.id = id;
        this.tipo = tipo;
        this.fornecedor = fornecedor;
        this.codigoReferencia = codigoReferencia;
        this.precoMetroCubico = precoMetroCubico;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    public String getCodigoReferencia() { return codigoReferencia; }
    public void setCodigoReferencia(String codigoReferencia) { this.codigoReferencia = codigoReferencia; }

    public BigDecimal getPrecoMetroCubico() { return precoMetroCubico; }
    public void setPrecoMetroCubico(BigDecimal precoMetroCubico) { this.precoMetroCubico = precoMetroCubico; }
}
