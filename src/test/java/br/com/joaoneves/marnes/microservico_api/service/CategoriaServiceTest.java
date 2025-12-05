package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.CategoriaDTO;
import br.com.joaoneves.marnes.microservico_api.model.Categoria;
import br.com.joaoneves.marnes.microservico_api.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    void setup() {
        categoria = new Categoria(1L, "Natural");
        categoriaDTO = new CategoriaDTO(1L, "Natural");
    }

    @Test
    @DisplayName("Deve listar todas as categorias com sucesso")
    void listarTodas_CenarioSucesso() {
        when(categoriaRepository.findAll()).thenReturn(List.of(categoria));
        List<CategoriaDTO> resultado = categoriaService.listarTodas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve criar uma nova categoria com sucesso")
    void criar_CenarioSucesso() {
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);
        CategoriaDTO resultado = categoriaService.criar(categoriaDTO);
        assertNotNull(resultado);
        assertEquals("Natural", resultado.nome());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }
}
