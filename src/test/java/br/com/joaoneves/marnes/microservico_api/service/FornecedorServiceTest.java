package br.com.joaoneves.marnes.microservico_api.service;

import br.com.joaoneves.marnes.microservico_api.dto.FornecedorRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.FornecedorResponseDTO;
import br.com.joaoneves.marnes.microservico_api.model.Fornecedor;
import br.com.joaoneves.marnes.microservico_api.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FornecedorServiceTest {

    @Mock
    private FornecedorRepository repository;

    private FornecedorService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new FornecedorService(repository);
    }

    @Test
    void salvar_devePersistirEDevolverDTO() {
        FornecedorRequestDTO req = new FornecedorRequestDTO();
        req.setNome("F1"); req.setContato("c1"); req.setEndereco("e1");

        doAnswer(invocation -> {
            Fornecedor f = invocation.getArgument(0);
            f.setId(5L);
            return f;
        }).when(repository).save(any(Fornecedor.class));

        FornecedorResponseDTO resp = service.salvar(req);

        assertNotNull(resp);
        assertEquals(5L, resp.getId());
        verify(repository).save(any(Fornecedor.class));
    }

    @Test
    void listarTodos_deveDelegarEConverter() {
        Fornecedor f1 = new Fornecedor(1L, "A", "c","e");
        Fornecedor f2 = new Fornecedor(2L, "B", "c","e");
        when(repository.findAll()).thenReturn(List.of(f1, f2));

        List<FornecedorResponseDTO> resp = service.listarTodos();

        assertEquals(2, resp.size());
        assertEquals("A", resp.get(0).getNome());
    }

    @Test
    void buscarPorId_quandoNaoExistir_deveLancar() {
        when(repository.findById(10L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(10L));
    }

    @Test
    void buscarPorId_quandoExistir_deveRetornarDTO() {
        when(repository.findById(3L)).thenReturn(Optional.of(new Fornecedor(3L, "F3","c","e")));
        var resp = service.buscarPorId(3L);
        assertNotNull(resp);
        assertEquals(3L, resp.getId());
    }
}
