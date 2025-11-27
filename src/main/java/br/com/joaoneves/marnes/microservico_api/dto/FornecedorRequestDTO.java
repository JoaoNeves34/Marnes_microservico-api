package br.com.joaoneves.marnes.microservico_api.dto;

import jakarta.validation.constraints.NotBlank;

public class FornecedorRequestDTO {

    @NotBlank(message = "O nome do fornecedor é obrigatório")
    private String nome;

    private String contato;

    private String endereco;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
