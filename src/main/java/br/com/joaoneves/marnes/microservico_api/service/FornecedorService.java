package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.FornecedorRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.FornecedorResponseDTO;
import br.com.joaoneves.marnes.microservico_api.model.Fornecedor;
import br.com.joaoneves.marnes.microservico_api.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public FornecedorResponseDTO salvar(FornecedorRequestDTO dto) {
        Fornecedor f = new Fornecedor();
        f.setNome(dto.getNome());
        f.setContato(dto.getContato());
        f.setEndereco(dto.getEndereco());
        repository.save(f);
        return new FornecedorResponseDTO(f);
    }

    public List<FornecedorResponseDTO> listarTodos() {
        return repository.findAll().stream().map(FornecedorResponseDTO::new).collect(Collectors.toList());
    }

    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor f = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
        return new FornecedorResponseDTO(f);
    }
}
