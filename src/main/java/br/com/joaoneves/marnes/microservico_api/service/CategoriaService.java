package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.CategoriaDTO;
import br.com.joaoneves.marnes.microservico_api.model.Categoria;
import br.com.joaoneves.marnes.microservico_api.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(cat -> new CategoriaDTO(cat.getId(), cat.getNome()))
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }
}