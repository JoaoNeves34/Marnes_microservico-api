package br.com.joaoneves.marnes.microservico_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.joaoneves.marnes.microservico_api.dto.MadeiraRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MadeiraControllerIntegrationTest {

    @LocalServerPort
    private int port;

    // create local ObjectMapper instead of autowiring (some test contexts may not expose it)
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void criarEListarFluxoBasico() throws Exception {
        // create a client and then create a fornecedor first so we can reference it when creating the madeira
        HttpClient client = HttpClient.newHttpClient();

        // create a fornecedor first so we can reference it when creating the madeira
        var fornecedorReq = new com.fasterxml.jackson.databind.node.ObjectNode(objectMapper.getNodeFactory());
        fornecedorReq.put("nome", "Madeireira Sul");
        fornecedorReq.put("contato", "(11) 99999-0000");
        fornecedorReq.put("endereco", "Rua A, 123");

        HttpRequest postFornecedor = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:" + port + "/fornecedores"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(fornecedorReq)))
            .build();

        HttpResponse<String> postFornecedorResp = client.send(postFornecedor, BodyHandlers.ofString());
        assertEquals(201, postFornecedorResp.statusCode());
        com.fasterxml.jackson.databind.JsonNode createdFornecedor = objectMapper.readTree(postFornecedorResp.body());
        Long fornecedorId = createdFornecedor.get("id").asLong();

        MadeiraRequestDTO dto = new MadeiraRequestDTO();
        dto.setTipo("Pinus Tratado");
        dto.setFornecedorId(fornecedorId);
        dto.setCodigoReferencia("MAD-2025-01");
        dto.setPrecoMetroCubico(new BigDecimal("129.90"));

        // HttpClient already created above

        HttpRequest postReq = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:" + port + "/madeiras"))
            .header("Content-Type", "application/json")
            .POST(BodyPublishers.ofString(objectMapper.writeValueAsString(dto)))
            .build();

        HttpResponse<String> postResp = client.send(postReq, BodyHandlers.ofString());
        assertEquals(201, postResp.statusCode());
        assertTrue(postResp.body().contains("Pinus Tratado"));

        HttpRequest getReq = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:" + port + "/madeiras"))
            .GET()
            .build();

        HttpResponse<String> listResp = client.send(getReq, BodyHandlers.ofString());
        assertEquals(200, listResp.statusCode());
        assertTrue(listResp.body().contains("Pinus Tratado"));
    }
}
