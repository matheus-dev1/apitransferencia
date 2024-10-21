package br.com.itaucase.apitranferencia.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ManipuladorGlobalExcecoesTest {

    private final ManipuladorGlobalExcecoes manipuladorGlobalExcecoes = new ManipuladorGlobalExcecoes();

    @Test
    public void testTratarTodasExcecoesEReturnaInternalServerError() {
        Exception exception = new Exception("Erro interno");

        ResponseEntity<String> response = manipuladorGlobalExcecoes.tratarTodasExcecoes(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno", response.getBody());
    }

    @Test
    public void testTratarTodasExcecoesEReturnaNullMessage() {
        Exception exception = new Exception();

        ResponseEntity<String> response = manipuladorGlobalExcecoes.tratarTodasExcecoes(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testTratarTodasExcecoesEReturnaEmptyMessage() {
        Exception exception = new Exception("");

        ResponseEntity<String> response = manipuladorGlobalExcecoes.tratarTodasExcecoes(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("", response.getBody());
    }
}