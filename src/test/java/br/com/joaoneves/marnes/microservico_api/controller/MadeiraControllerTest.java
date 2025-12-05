package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraDTO;
import br.com.joaoneves.marnes.microservico_api.service.MadeiraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MadeiraController.class)
class MadeiraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MadeiraService madeiraService;

    // Dados de Teste
    private final MadeiraDTO madeiraDTO = new MadeiraDTO(
            1L, "Ipê", "Brasil", "Alta", "Alta", "Marrom", 1L
    );
    private final MadeiraDTO dtoInvalido = new MadeiraDTO(
            null, "", "", "Alta", "Alta", "Marrom", 1L
    );

    @Test
    @DisplayName("POST /api/madeiras - Deve criar uma nova madeira e retornar 201")
    void criar_CenarioSucesso() throws Exception {
        when(madeiraService.criar(any(MadeiraDTO.class))).thenReturn(madeiraDTO);

        String json = objectMapper.writeValueAsString(madeiraDTO);

        mockMvc.perform(post("/api/madeiras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated()) // Espera 201 CREATED
                .andExpect(jsonPath("$.nome").value("Ipê"));
    }

    @Test
    @DisplayName("POST /api/madeiras - Deve retornar 400 se o DTO for inválido (Validação)")
    void criar_CenarioErroValidacao() throws Exception {
        String json = objectMapper.writeValueAsString(dtoInvalido);
        
        mockMvc.perform(post("/api/madeiras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest()) // Espera 400 BAD REQUEST
                .andExpect(jsonPath("$.nome").exists());
    }

    @Test
    @DisplayName("GET /api/madeiras - Deve listar todas as madeiras e retornar 200")
    void listar_CenarioSucesso() throws Exception {
        when(madeiraService.listarTodas()).thenReturn(List.of(madeiraDTO));

        mockMvc.perform(get("/api/madeiras")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera 200 OK
                .andExpect(jsonPath("$[0].nome").value("Ipê"));
    }
    
    @Test
    @DisplayName("GET /api/madeiras/filtro - Deve filtrar por categoria e retornar 200")
    void filtrar_CenarioSucesso() throws Exception {
        when(madeiraService.listarPorCategoria(1L)).thenReturn(List.of(madeiraDTO));

        mockMvc.perform(get("/api/madeiras/filtro")
                        .param("categoriaId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Ipê"));
    }

    @Test
    @DisplayName("PUT /api/madeiras/{id} - Deve atualizar e retornar 200")
    void atualizar_CenarioSucesso() throws Exception {
        when(madeiraService.atualizar(eq(1L), any(MadeiraDTO.class))).thenReturn(madeiraDTO);
        String json = objectMapper.writeValueAsString(madeiraDTO);

        mockMvc.perform(put("/api/madeiras/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ipê"));
    }

    @Test
    @DisplayName("PUT /api/madeiras/{id} - Deve retornar 404 se ID inexistente")
    void atualizar_CenarioErroNotFound() throws Exception {
        when(madeiraService.atualizar(eq(99L), any(MadeiraDTO.class))).thenThrow(new EntityNotFoundException("Não achado"));
        String json = objectMapper.writeValueAsString(madeiraDTO);

        mockMvc.perform(put("/api/madeiras/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound()); // Espera 404 NOT FOUND
    }

    @Test
    @DisplayName("DELETE /api/madeiras/{id} - Deve deletar e retornar 204")
    void deletar_CenarioSucesso() throws Exception {
        doNothing().when(madeiraService).deletar(1L);

        mockMvc.perform(delete("/api/madeiras/1"))
                .andExpect(status().isNoContent()); // Espera 204 NO CONTENT
    }

    @Test
    @DisplayName("DELETE /api/madeiras/{id} - Deve retornar 404 se ID não existe")
    void deletar_CenarioErroNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Madeira não encontrada")).when(madeiraService).deletar(99L);

        mockMvc.perform(delete("/api/madeiras/99"))
                .andExpect(status().isNotFound());
    }
}
