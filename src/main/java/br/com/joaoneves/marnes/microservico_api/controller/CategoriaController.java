package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.CategoriaDTO;
import br.com.joaoneves.marnes.microservico_api.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Gerenciamento de tipos de madeira")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @Operation(summary = "Criar nova categoria")
    public ResponseEntity<CategoriaDTO> criar(@RequestBody @Valid CategoriaDTO dto) {
        CategoriaDTO novaCategoria = categoriaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @GetMapping
    @Operation(summary = "Listar categorias")
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }
}