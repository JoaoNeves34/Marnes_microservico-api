package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.MadeiraResponseDTO;
import br.com.joaoneves.marnes.microservico_api.model.Fornecedor;
import br.com.joaoneves.marnes.microservico_api.service.MadeiraService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class MadeiraControllerTest {

    private MadeiraService service;
    private MadeiraController controller;

    @org.junit.jupiter.api.BeforeEach
    void setup() {
        service = org.mockito.Mockito.mock(MadeiraService.class);
        controller = new MadeiraController(service);
    }

    @org.junit.jupiter.api.Test
    void criar_deveRetornar201() {
        MadeiraRequestDTO req = new MadeiraRequestDTO();
        req.setTipo("Pinus"); req.setFornecedorId(1L); req.setCodigoReferencia("C1"); req.setPrecoMetroCubico(new BigDecimal("10"));

        org.mockito.Mockito.when(service.salvar(org.mockito.ArgumentMatchers.any(MadeiraRequestDTO.class)))
                .thenReturn(new MadeiraResponseDTO(new br.com.joaoneves.marnes.microservico_api.model.Madeira(1L, "Pinus", new Fornecedor(1L,"F","c","e"), "C1", new BigDecimal("10"))));

        var resp = controller.criar(req);
        assertEquals(201, resp.getStatusCode().value());
        assertTrue(resp.getBody().getTipo().contains("Pinus"));
    }

    @org.junit.jupiter.api.Test
    void listar_deveRetornarLista() {
        var dto = new MadeiraResponseDTO(new br.com.joaoneves.marnes.microservico_api.model.Madeira(2L, "Cedar", new Fornecedor(1L,"F","c","e"), "C2", new BigDecimal("20")));
        org.mockito.Mockito.when(service.listarTodas()).thenReturn(List.of(dto));

        var resp = controller.listarTodas();
        assertEquals(200, resp.getStatusCode().value());
        assertTrue(resp.getBody().stream().anyMatch(r -> "Cedar".equals(r.getTipo())));
    }

    @org.junit.jupiter.api.Test
    void buscarPorTipo_deveDelegar() {
        var dto = new MadeiraResponseDTO(new br.com.joaoneves.marnes.microservico_api.model.Madeira(3L, "Carvalho", new Fornecedor(2L,"F2","c","e"), "C3", new BigDecimal("30")));
        org.mockito.Mockito.when(service.buscarPorTipo("Carvalho")).thenReturn(List.of(dto));

        var resp = controller.buscarPorTipo("Carvalho");
        assertEquals(200, resp.getStatusCode().value());
        assertTrue(resp.getBody().stream().anyMatch(r -> "Carvalho".equals(r.getTipo())));
    }

    @org.junit.jupiter.api.Test
    void deletar_deveRetornar204() {
        var resp = controller.deletar(99L);
        assertEquals(204, resp.getStatusCode().value());
    }
}
