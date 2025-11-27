package br.com.joaoneves.marnes.microservico_api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleEntityNotFound_deveRetornar404() {
        var resp = handler.handleEntityNotFound(new EntityNotFoundException("not found"));
        assertEquals(404, resp.getStatusCode().value());
        assertTrue(resp.getBody().contains("not found"));
    }

    @Test
    void handleValidationExceptions_deveMontarMapa() {
        BindingResult binding = mock(BindingResult.class);
        FieldError fe = new FieldError("obj","campo","mensagem");
        when(binding.getAllErrors()).thenReturn(List.of(fe));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(binding);

        var resp = handler.handleValidationExceptions(ex);
        assertEquals(400, resp.getStatusCode().value());
        assertTrue(resp.getBody().containsKey("campo"));
    }
}
