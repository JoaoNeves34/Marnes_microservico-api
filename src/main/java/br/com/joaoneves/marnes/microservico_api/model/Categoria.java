package br.com.joaoneves.marnes.microservico_api.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore; // NOVO IMPORT

@Entity
@Table(name = "tb_categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    // A SOLUÇÃO: Ignora esta lista na hora de serializar para JSON, quebrando o loop.
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Madeira> madeiras;

    public Categoria() {}

    // Construtor e Getters/Setters (omiti para brevidade, mas devem estar no seu arquivo)
    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<Madeira> getMadeiras() { return madeiras; }
    public void setMadeiras(List<Madeira> madeiras) { this.madeiras = madeiras; }
}