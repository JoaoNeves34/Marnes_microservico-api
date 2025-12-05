package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraDTO;
import br.com.joaoneves.marnes.microservico_api.service.MadeiraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/madeiras")
@Tag(name = "Madeiras", description = "Gerenciamento do catálogo de madeiras")
public class MadeiraController {

    private final MadeiraService madeiraService;

    public MadeiraController(MadeiraService madeiraService) {
        this.madeiraService = madeiraService;
    }

    @GetMapping
    @Operation(summary = "Listar madeiras")
    public ResponseEntity<List<MadeiraDTO>> listar() {
        return ResponseEntity.ok(madeiraService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar madeira por ID")
    public ResponseEntity<MadeiraDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(madeiraService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar madeira")
    public ResponseEntity<MadeiraDTO> criar(@RequestBody @Valid MadeiraDTO dto) {
        MadeiraDTO novaMadeira = madeiraService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMadeira);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar madeira")
    public ResponseEntity<MadeiraDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MadeiraDTO dto) {
        MadeiraDTO madeiraAtualizada = madeiraService.atualizar(id, dto);
        return ResponseEntity.ok(madeiraAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar madeira")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        madeiraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/filtro")
    @Operation(summary = "Filtrar madeiras por categoria")
    public ResponseEntity<List<MadeiraDTO>> filtrarPorCategoria(@RequestParam Long categoriaId) {
        return ResponseEntity.ok(madeiraService.listarPorCategoria(categoriaId));
    }
}