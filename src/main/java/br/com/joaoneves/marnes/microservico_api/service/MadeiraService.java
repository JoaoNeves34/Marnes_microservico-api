package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.MadeiraResponseDTO;
import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import br.com.joaoneves.marnes.microservico_api.repository.MadeiraRepository;
import br.com.joaoneves.marnes.microservico_api.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MadeiraService {

    private final MadeiraRepository repository;
    private final FornecedorRepository fornecedorRepository;

    public MadeiraService(MadeiraRepository repository, FornecedorRepository fornecedorRepository) {
        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<MadeiraResponseDTO> listarTodas() {
        return repository.findAll().stream().map(MadeiraResponseDTO::new).collect(Collectors.toList());
    }

    public MadeiraResponseDTO buscarPorId(Long id) {
        Madeira madeira = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Madeira não encontrada"));
        return new MadeiraResponseDTO(madeira);
    }

    public MadeiraResponseDTO salvar(MadeiraRequestDTO dto) {
        var fornecedor = fornecedorRepository.findById(dto.getFornecedorId()).orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Fornecedor não encontrado"));
        Madeira madeira = new Madeira();
        madeira.setTipo(dto.getTipo());
        madeira.setFornecedor(fornecedor);
        madeira.setCodigoReferencia(dto.getCodigoReferencia());
        madeira.setPrecoMetroCubico(dto.getPrecoMetroCubico());

        repository.save(madeira);
        return new MadeiraResponseDTO(madeira);
    }

    public MadeiraResponseDTO atualizar(Long id, MadeiraRequestDTO dto) {
        Madeira madeira = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Madeira não encontrada"));
        // keep current fornecedor association unless caller explicitly changes fornecedor separately
        madeira.setTipo(dto.getTipo());
        // if passed a different fornecedor id, update association
        if (dto.getFornecedorId() != null) {
            var f = fornecedorRepository.findById(dto.getFornecedorId()).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
            madeira.setFornecedor(f);
        }
        madeira.setPrecoMetroCubico(dto.getPrecoMetroCubico());

        repository.save(madeira);
        return new MadeiraResponseDTO(madeira);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Madeira não encontrada");
        }
        repository.deleteById(id);
    }

    public List<MadeiraResponseDTO> buscarPorTipo(String tipo) {
        return repository.findByTipoContainingIgnoreCase(tipo).stream().map(MadeiraResponseDTO::new).collect(Collectors.toList());
    }
}
