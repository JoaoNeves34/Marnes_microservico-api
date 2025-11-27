package br.com.joaoneves.marnes.microservico_api.dto;

import br.com.joaoneves.marnes.microservico_api.model.Fornecedor;

public class FornecedorResponseDTO {
    private Long id;
    private String nome;
    private String contato;
    private String endereco;

    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.contato = fornecedor.getContato();
        this.endereco = fornecedor.getEndereco();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getContato() { return contato; }
    public String getEndereco() { return endereco; }
}
