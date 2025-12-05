package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraDTO;
import br.com.joaoneves.marnes.microservico_api.model.Categoria;
import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import br.com.joaoneves.marnes.microservico_api.repository.CategoriaRepository;
import br.com.joaoneves.marnes.microservico_api.repository.MadeiraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MadeiraServiceTest {

    @Mock
    private MadeiraRepository madeiraRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private MadeiraService madeiraService;

    private Madeira madeira;
    private Categoria categoria;
    private MadeiraDTO madeiraDTO;

    @BeforeEach
    void setup() {
        categoria = new Categoria(1L, "Natural");
        madeira = new Madeira(1L, "Ipê", "Brasil", "Alta", "Alta", "Marrom", categoria);
        madeiraDTO = new MadeiraDTO(1L, "Ipê", "Brasil", "Alta", "Alta", "Marrom", 1L);
    }

    @Test
    @DisplayName("Deve listar todas as madeiras com sucesso")
    void listarTodas_CenarioSucesso() {
        when(madeiraRepository.findAll()).thenReturn(List.of(madeira));
        List<MadeiraDTO> resultado = madeiraService.listarTodas();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Deve buscar madeira por ID com sucesso")
    void buscarPorId_CenarioSucesso() {
        when(madeiraRepository.findById(1L)).thenReturn(Optional.of(madeira));
        MadeiraDTO resultado = madeiraService.buscarPorId(1L);
        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar ID inexistente")
    void buscarPorId_CenarioErro() {
        when(madeiraRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> madeiraService.buscarPorId(99L));
    }

    @Test
    @DisplayName("Deve criar madeira com sucesso")
    void criar_CenarioSucesso() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(madeiraRepository.save(any(Madeira.class))).thenReturn(madeira);
        MadeiraDTO resultado = madeiraService.criar(madeiraDTO);
        assertNotNull(resultado);
        verify(madeiraRepository, times(1)).save(any(Madeira.class));
    }

    @Test
    @DisplayName("Deve lançar erro ao criar madeira com Categoria inexistente")
    void criar_CenarioErroCategoria() {
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> madeiraService.criar(madeiraDTO));
        verify(madeiraRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve atualizar madeira com sucesso")
    void atualizar_CenarioSucesso() {
        when(madeiraRepository.findById(1L)).thenReturn(Optional.of(madeira));
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(madeiraRepository.save(any(Madeira.class))).thenReturn(madeira);
        MadeiraDTO resultado = madeiraService.atualizar(1L, madeiraDTO);
        assertEquals("Ipê", resultado.nome());
    }
    
    @Test
    @DisplayName("Deve lançar erro ao atualizar madeira inexistente")
    void atualizar_CenarioErroMadeiraNaoExiste() {
        when(madeiraRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> madeiraService.atualizar(99L, madeiraDTO));
        verify(madeiraRepository, never()).save(any());
    }
    
    @Test
    @DisplayName("Deve deletar madeira com sucesso")
    void deletar_CenarioSucesso() {
        when(madeiraRepository.existsById(1L)).thenReturn(true);
        madeiraService.deletar(1L);
        verify(madeiraRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar erro ao deletar ID inexistente")
    void deletar_CenarioErro() {
        when(madeiraRepository.existsById(99L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> madeiraService.deletar(99L));
        verify(madeiraRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve filtrar madeiras por categoria (Rota GET /filtro)")
    void filtrarPorCategoria_CenarioSucesso() {
        when(categoriaRepository.existsById(1L)).thenReturn(true);
        when(madeiraRepository.findByCategoriaId(1L)).thenReturn(List.of(madeira));
        List<MadeiraDTO> resultado = madeiraService.listarPorCategoria(1L);
        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve lançar erro ao filtrar por Categoria inexistente")
    void filtrarPorCategoria_CenarioErroCategoria() {
        when(categoriaRepository.existsById(99L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> madeiraService.listarPorCategoria(99L));
        verify(madeiraRepository, never()).findByCategoriaId(anyLong());
    }
}