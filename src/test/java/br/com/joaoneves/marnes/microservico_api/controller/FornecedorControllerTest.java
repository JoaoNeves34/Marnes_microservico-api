package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.FornecedorRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.FornecedorResponseDTO;
import br.com.joaoneves.marnes.microservico_api.service.FornecedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FornecedorControllerTest {

    private FornecedorService service;
    private FornecedorController controller;

    @BeforeEach
    void setup() {
        service = mock(FornecedorService.class);
        controller = new FornecedorController(service);
    }

    @Test
    void criar_deveRetornarCreated() {
        FornecedorRequestDTO req = new FornecedorRequestDTO();
        req.setNome("F1");

        when(service.salvar(any(FornecedorRequestDTO.class))).thenReturn(new FornecedorResponseDTO(new br.com.joaoneves.marnes.microservico_api.model.Fornecedor(1L, "F1","c","e")));

        var resp = controller.criar(req);
        assertEquals(201, resp.getStatusCode().value());
        assertTrue(resp.getBody().getNome().contains("F1"));
    }

    @Test
    void listar_deveRetornarLista() {
        var f1 = new FornecedorResponseDTO(new br.com.joaoneves.marnes.microservico_api.model.Fornecedor(1L, "A","c","e"));
        when(service.listarTodos()).thenReturn(List.of(f1));

        var resp = controller.listarTodos();

        assertEquals(200, resp.getStatusCode().value());
        assertTrue(resp.getBody().stream().anyMatch(r -> "A".equals(r.getNome())));
    }
}
