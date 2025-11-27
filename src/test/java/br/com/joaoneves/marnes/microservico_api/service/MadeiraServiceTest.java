package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.MadeiraResponseDTO;
import br.com.joaoneves.marnes.microservico_api.model.Madeira;
import br.com.joaoneves.marnes.microservico_api.model.Fornecedor;
import br.com.joaoneves.marnes.microservico_api.repository.MadeiraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MadeiraServiceTest {

    @Mock
    private MadeiraRepository repository;

    @Mock
    private br.com.joaoneves.marnes.microservico_api.repository.FornecedorRepository fornecedorRepository;

    private MadeiraService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new MadeiraService(repository, fornecedorRepository);
    }

    @Test
    void listarTodas_deveRetornarListaVaziaQuandoNaoExistir() {
        when(repository.findAll()).thenReturn(List.of());

        List<MadeiraResponseDTO> result = service.listarTodas();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void salvar_deveCriarMadeiraERetornarResponse() {
        MadeiraRequestDTO dto = new MadeiraRequestDTO();
        dto.setTipo("Mogno");
        dto.setFornecedorId(1L);
        dto.setCodigoReferencia("MAD-2024-001");
        dto.setPrecoMetroCubico(new BigDecimal("199.90"));

        // mock fornecedor lookup
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(new Fornecedor(1L, "Madeireira Sul", "ct", "end")));

        // fornecedor must exist for salvar() to succeed
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(new Fornecedor(1L, "Fornecedor Test", "contato", "endereco")));

        // mock fornecedor lookup
        Fornecedor fornecedor = new Fornecedor(1L, "Fornecedor Test", "cont", "end");
        when(fornecedorRepository.findById(1L)).thenReturn(Optional.of(fornecedor));

        doAnswer(invocation -> {
            Madeira arg = invocation.getArgument(0);
            arg.setId(1L);
            return arg;
        }).when(repository).save(any(Madeira.class));

        MadeiraResponseDTO resp = service.salvar(dto);

        assertNotNull(resp);
        assertEquals(1L, resp.getId());
        assertEquals("Mogno", resp.getTipo());
        verify(repository).save(any(Madeira.class));
    }

    @Test
    void buscarPorId_quandoExistirRetornaDTO() {
        Fornecedor f = new Fornecedor(1L, "Fornecedor X", "contato", "endereco");
        Madeira madeira = new Madeira(1L, "Pinus", f, "MAD-01", new BigDecimal("50"));
        when(repository.findById(1L)).thenReturn(Optional.of(madeira));

        MadeiraResponseDTO dto = service.buscarPorId(1L);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
    }

    @Test
    void buscarPorId_quandoNaoExistir_deveLancarEntityNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void atualizar_quandoNaoExistir_deveLancarEntityNotFound() {
        MadeiraRequestDTO dto = new MadeiraRequestDTO();
        dto.setTipo("X"); dto.setFornecedorId(2L); dto.setCodigoReferencia("Z"); dto.setPrecoMetroCubico(new BigDecimal("1"));

        when(repository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.atualizar(5L, dto));
    }

    @Test
    void atualizar_quandoExistir_deveAtualizarEDevolver() {
        Fornecedor oldF = new Fornecedor(1L, "Old", "c", "e");
        Madeira existing = new Madeira(10L, "Tipo", oldF, "REF1", new BigDecimal("100"));
        when(repository.findById(10L)).thenReturn(Optional.of(existing));

        // change fornecedor
        Fornecedor newF = new Fornecedor(2L, "NewF", "c","e");
        when(fornecedorRepository.findById(2L)).thenReturn(Optional.of(newF));

        MadeiraRequestDTO dto = new MadeiraRequestDTO();
        dto.setTipo("TipoNew"); dto.setFornecedorId(2L); dto.setCodigoReferencia("REF1"); dto.setPrecoMetroCubico(new BigDecimal("120"));

        doAnswer(invocation -> invocation.getArgument(0)).when(repository).save(any(Madeira.class));

        var resp = service.atualizar(10L, dto);
        assertEquals(10L, resp.getId());
        assertEquals("TipoNew", resp.getTipo());
    }

    @Test
    void deletar_quandoNaoExistir_deveLancarEntityNotFound() {
        when(repository.existsById(10L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.deletar(10L));
    }

    @Test
    void deletar_quandoExistir_deveRemover() {
        when(repository.existsById(20L)).thenReturn(true);
        // should not throw
        service.deletar(20L);
    }

    @Test
    void buscarPorTipo_deveDelegarParaRepository() {
        Fornecedor f2 = new Fornecedor(3L, "Fornecedor A", "ct", "ed");
        Madeira madeira = new Madeira(2L, "Carvalho", f2, "MAD-002", new BigDecimal("300"));
        when(repository.findByTipoContainingIgnoreCase("Carvalho")).thenReturn(List.of(madeira));

        List<MadeiraResponseDTO> list = service.buscarPorTipo("Carvalho");

        assertEquals(1, list.size());
        assertEquals("Carvalho", list.get(0).getTipo());
    }
}
