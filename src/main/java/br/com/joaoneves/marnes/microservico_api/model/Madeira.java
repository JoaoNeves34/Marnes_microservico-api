package br.com.joaoneves.marnes.microservico_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_madeiras")
public class Madeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, length = 50)
    private String origem;
    @Column(nullable = false, length = 30)
    private String densidade;
    @Column(nullable = false, length = 50)
    private String resistencia;
    @Column(nullable = false, length = 30)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Madeira() {}
    public Madeira(Long id, String nome, String origem, String densidade, String resistencia, String cor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.origem = origem;
        this.densidade = densidade;
        this.resistencia = resistencia;
        this.cor = cor;
        this.categoria = categoria;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    public String getDensidade() { return densidade; }
    public void setDensidade(String densidade) { this.densidade = densidade; }
    public String getResistencia() { return resistencia; }
    public void setResistencia(String resistencia) { this.resistencia = resistencia; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}