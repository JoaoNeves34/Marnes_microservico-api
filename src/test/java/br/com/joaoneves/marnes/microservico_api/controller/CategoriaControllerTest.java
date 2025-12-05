package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.CategoriaDTO;
import br.com.joaoneves.marnes.microservico_api.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategoriaService categoriaService;

    private final CategoriaDTO categoriaDTO = new CategoriaDTO(1L, "Natural");
    private final CategoriaDTO dtoInvalido = new CategoriaDTO(null, "");

    @Test
    @DisplayName("POST /api/categorias - Deve criar uma categoria e retornar 201")
    void criar_CenarioSucesso() throws Exception {
        when(categoriaService.criar(any(CategoriaDTO.class))).thenReturn(categoriaDTO);
        String json = objectMapper.writeValueAsString(categoriaDTO);

        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Natural"));
    }

    @Test
    @DisplayName("POST /api/categorias - Deve retornar 400 se o DTO for inválido")
    void criar_CenarioErroValidacao() throws Exception {
        String json = objectMapper.writeValueAsString(dtoInvalido);

        mockMvc.perform(post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").exists());
    }

    @Test
    @DisplayName("GET /api/categorias - Deve listar categorias e retornar 200")
    void listar_CenarioSucesso() throws Exception {
        when(categoriaService.listarTodas()).thenReturn(List.of(categoriaDTO));

        mockMvc.perform(get("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Natural"));
    }
}
