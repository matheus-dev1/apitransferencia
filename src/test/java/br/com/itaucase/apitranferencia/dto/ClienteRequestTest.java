package br.com.itaucase.apitranferencia.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteRequestTest {
    
    private ClienteRequest clienteRequest;

    @BeforeEach
    public void setUp() {
        clienteRequest = new ClienteRequest();
    }

    @Test
    public void testConstructor() {
        assertNotNull(clienteRequest);
        assertNull(clienteRequest.getId());
        assertNull(clienteRequest.getNome());
        assertNull(clienteRequest.getNumeroConta());
        assertNull(clienteRequest.getSaldo());
    }

    @Test
    public void testConstructorComParametros() {
        String id = "1";
        String nome = "João";
        String numeroConta = "123456";
        Double saldo = 500.0;

        ClienteRequest request = new ClienteRequest(id, nome, numeroConta, saldo);

        assertEquals(id, request.getId());
        assertEquals(nome, request.getNome());
        assertEquals(numeroConta, request.getNumeroConta());
        assertEquals(saldo, request.getSaldo());
    }

    @Test
    public void testGettersAndSetters() {
        String id = "1";
        String nome = "João";
        String numeroConta = "123456";
        Double saldo = 500.0;

        clienteRequest.setId(id);
        clienteRequest.setNome(nome);
        clienteRequest.setNumeroConta(numeroConta);
        clienteRequest.setSaldo(saldo);

        assertEquals(id, clienteRequest.getId());
        assertEquals(nome, clienteRequest.getNome());
        assertEquals(numeroConta, clienteRequest.getNumeroConta());
        assertEquals(saldo, clienteRequest.getSaldo());
    }

    @Test
    public void testSetId() {
        String id = "1";

        clienteRequest.setId(id);

        assertEquals(id, clienteRequest.getId());
    }

    @Test
    public void testSetNome() {
        String nome = "João";

        clienteRequest.setNome(nome);

        assertEquals(nome, clienteRequest.getNome());
    }

    @Test
    public void testSetNumeroConta() {
        String numeroConta = "123456";

        clienteRequest.setNumeroConta(numeroConta);

        assertEquals(numeroConta, clienteRequest.getNumeroConta());
    }

    @Test
    public void testSetSaldo() {
        Double saldo = 500.0;

        clienteRequest.setSaldo(saldo);

        assertEquals(saldo, clienteRequest.getSaldo());
    }

    @Test
    public void testSetSaldoNegativo() {
        Double saldo = -100.0;

        clienteRequest.setSaldo(saldo);

        assertEquals(saldo, clienteRequest.getSaldo());
    }

    @Test
    public void testSetValoresNulos() {
        clienteRequest.setId(null);
        clienteRequest.setNome(null);
        clienteRequest.setNumeroConta(null);
        clienteRequest.setSaldo(null);

        assertNull(clienteRequest.getId());
        assertNull(clienteRequest.getNome());
        assertNull(clienteRequest.getNumeroConta());
        assertNull(clienteRequest.getSaldo());
    }
}