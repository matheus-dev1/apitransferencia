package br.com.itaucase.apitranferencia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
    }

    @Test
    public void testConstructorComParametros() {
        String id = "clientId";
        String nome = "John Doe";
        String numeroConta = "123456";
        Double saldo = 5000.0;

        Cliente cliente = new Cliente(id, nome, numeroConta, saldo);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(numeroConta, cliente.getNumeroConta());
        assertEquals(saldo, cliente.getSaldo());
    }

    @Test
    public void testGettersAndSetters() {
        String id = "clientId";
        String nome = "John Doe";
        String numeroConta = "123456";
        Double saldo = 5000.0;

        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setNumeroConta(numeroConta);
        cliente.setSaldo(saldo);

        assertEquals(id, cliente.getId());
        assertEquals(nome, cliente.getNome());
        assertEquals(numeroConta, cliente.getNumeroConta());
        assertEquals(saldo, cliente.getSaldo());
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(cliente);
        assertNull(cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getNumeroConta());
        assertNull(cliente.getSaldo());
    }

    @Test
    public void testSetId() {
        String id = "clientId";
        
        cliente.setId(id);
        
        assertEquals(id, cliente.getId());
    }

    @Test
    public void testSetNome() {
        String nome = "John Doe";
        
        cliente.setNome(nome);
        
        assertEquals(nome, cliente.getNome());
    }

    @Test
    public void testSetNumeroConta() {
        String numeroConta = "123456";
        
        cliente.setNumeroConta(numeroConta);
        
        assertEquals(numeroConta, cliente.getNumeroConta());
    }

    @Test
    public void testSetSaldo() {
        Double saldo = 5000.0;
        
        cliente.setSaldo(saldo);
        
        assertEquals(saldo, cliente.getSaldo());
    }

    @Test
    public void testSetSaldoNegativo() {
        Double saldo = -500.0;
        
        cliente.setSaldo(saldo);
        
        assertEquals(saldo, cliente.getSaldo());
    }
}