package br.com.joaoneves.marnes.microservico_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MadeiraDTO(
    Long id,
    @NotBlank(message = "O nome do material é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    String nome,
    @NotBlank(message = "A origem é obrigatória")
    @Size(min = 3, max = 50, message = "A origem deve ter entre 3 e 50 caracteres")
    String origem,
    @NotBlank(message = "A densidade é obrigatória")
    @Size(max = 30, message = "A densidade deve ter no máximo 30 caracteres")
    String densidade,
    @NotBlank(message = "A resistência é obrigatória")
    @Size(max = 50, message = "A resistência deve ter no máximo 50 caracteres")
    String resistencia,
    @NotBlank(message = "A cor é obrigatória")
    @Size(max = 30, message = "A cor deve ter no máximo 30 caracteres")
    String cor,
    @NotNull(message = "O ID da categoria é obrigatório")
    Long categoriaId
) {}