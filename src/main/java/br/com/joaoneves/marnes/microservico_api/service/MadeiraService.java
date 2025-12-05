package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraDTO;
import br.com.joaoneves.marnes.microservico_api.model.Categoria;
import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import br.com.joaoneves.marnes.microservico_api.repository.CategoriaRepository;
import br.com.joaoneves.marnes.microservico_api.repository.MadeiraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MadeiraService {

    private final MadeiraRepository madeiraRepository;
    private final CategoriaRepository categoriaRepository;

    public MadeiraService(MadeiraRepository madeiraRepository, CategoriaRepository categoriaRepository) {
        this.madeiraRepository = madeiraRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<MadeiraDTO> listarTodas() {
        return madeiraRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MadeiraDTO buscarPorId(Long id) {
        Madeira madeira = madeiraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Madeira não encontrada com id: " + id));
        return converterParaDTO(madeira);
    }

    @Transactional
    public MadeiraDTO criar(MadeiraDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + dto.categoriaId()));

        Madeira madeira = new Madeira();
        atualizarDadosEntidade(madeira, dto, categoria);
        madeira = madeiraRepository.save(madeira);
        return converterParaDTO(madeira);
    }

    @Transactional
    public MadeiraDTO atualizar(Long id, MadeiraDTO dto) {
        Madeira madeira = madeiraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Madeira não encontrada com id: " + id));
        
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com id: " + dto.categoriaId()));

        atualizarDadosEntidade(madeira, dto, categoria);
        madeira = madeiraRepository.save(madeira);
        return converterParaDTO(madeira);
    }

    @Transactional
    public void deletar(Long id) {
        if (!madeiraRepository.existsById(id)) {
            throw new EntityNotFoundException("Madeira não encontrada com id: " + id);
        }
        madeiraRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MadeiraDTO> listarPorCategoria(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new EntityNotFoundException("Categoria não encontrada com id: " + categoriaId);
        }
        return madeiraRepository.findByCategoriaId(categoriaId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private void atualizarDadosEntidade(Madeira madeira, MadeiraDTO dto, Categoria categoria) {
        madeira.setNome(dto.nome());
        madeira.setOrigem(dto.origem());
        madeira.setDensidade(dto.densidade());
        madeira.setResistencia(dto.resistencia());
        madeira.setCor(dto.cor());
        madeira.setCategoria(categoria);
    }

    private MadeiraDTO converterParaDTO(Madeira madeira) {
        return new MadeiraDTO(
                madeira.getId(),
                madeira.getNome(),
                madeira.getOrigem(),
                madeira.getDensidade(),
                madeira.getResistencia(),
                madeira.getCor(),
                madeira.getCategoria().getId()
        );
    }
}