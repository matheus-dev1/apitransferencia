package br.com.itaucase.apitranferencia.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferenciaRequestTest {
    
    private TransferenciaRequest transferenciaRequest;

    @BeforeEach
    public void setUp() {
        transferenciaRequest = new TransferenciaRequest();
    }

    @Test
    public void testConstructor() {
        assertNotNull(transferenciaRequest);
        assertNull(transferenciaRequest.getContaOrigem());
        assertNull(transferenciaRequest.getContaDestino());
        assertNull(transferenciaRequest.getValor());
    }

    @Test
    public void testConstructorComParametros() {
        String contaOrigem = "123456";
        String contaDestino = "654321";
        Double valor = 1000.0;

        TransferenciaRequest request = new TransferenciaRequest(contaOrigem, contaDestino, valor);

        assertEquals(contaOrigem, request.getContaOrigem());
        assertEquals(contaDestino, request.getContaDestino());
        assertEquals(valor, request.getValor());
    }

    @Test
    public void testGettersAndSetters() {
        String contaOrigem = "123456";
        String contaDestino = "654321";
        Double valor = 1000.0;

        transferenciaRequest.setContaOrigem(contaOrigem);
        transferenciaRequest.setContaDestino(contaDestino);
        transferenciaRequest.setValor(valor);

        assertEquals(contaOrigem, transferenciaRequest.getContaOrigem());
        assertEquals(contaDestino, transferenciaRequest.getContaDestino());
        assertEquals(valor, transferenciaRequest.getValor());
    }

    @Test
    public void testSetContaOrigem() {
        String contaOrigem = "123456";

        transferenciaRequest.setContaOrigem(contaOrigem);

        assertEquals(contaOrigem, transferenciaRequest.getContaOrigem());
    }

    @Test
    public void testSetContaDestino() {
        String contaDestino = "654321";

        transferenciaRequest.setContaDestino(contaDestino);

        assertEquals(contaDestino, transferenciaRequest.getContaDestino());
    }

    @Test
    public void testSetValor() {
        Double valor = 1000.0;

        transferenciaRequest.setValor(valor);
        
        assertEquals(valor, transferenciaRequest.getValor());
    }

    @Test
    public void testSetValorNegativo() {
        Double valor = -1000.0;

        transferenciaRequest.setValor(valor);

        assertEquals(valor, transferenciaRequest.getValor());
    }

    @Test
    public void testSetValoresNulos() {
        transferenciaRequest.setContaOrigem(null);
        transferenciaRequest.setContaDestino(null);
        transferenciaRequest.setValor(null);

        assertNull(transferenciaRequest.getContaOrigem());
        assertNull(transferenciaRequest.getContaDestino());
        assertNull(transferenciaRequest.getValor());
    }
}