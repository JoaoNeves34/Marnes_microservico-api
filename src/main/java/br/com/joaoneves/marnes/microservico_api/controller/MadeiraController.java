package br.com.joaoneves.marnes.microservico_api.controller;

import br.com.joaoneves.marnes.microservico_api.dto.MadeiraRequestDTO;
import br.com.joaoneves.marnes.microservico_api.dto.MadeiraResponseDTO;
import br.com.joaoneves.marnes.microservico_api.service.MadeiraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/madeiras")
public class MadeiraController {

    private final MadeiraService service;

    public MadeiraController(MadeiraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MadeiraResponseDTO> criar(@RequestBody @Valid MadeiraRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MadeiraResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MadeiraResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MadeiraResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid MadeiraRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo/{nomeTipo}")
    public ResponseEntity<List<MadeiraResponseDTO>> buscarPorTipo(@PathVariable String nomeTipo) {
        return ResponseEntity.ok(service.buscarPorTipo(nomeTipo));
    }
}
