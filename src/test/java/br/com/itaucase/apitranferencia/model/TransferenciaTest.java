package br.com.itaucase.apitranferencia.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferenciaTest {

    private Transferencia transferencia;

    @BeforeEach
    public void setUp() {
        transferencia = new Transferencia();
    }

    @Test
    public void testConstructorComParametros() {
        String id = "transferId";
        String contaOrigem = "12345";
        String contaDestino = "54321";
        Double valor = 1000.0;
        String data = "2023-10-20";
        Boolean sucesso = true;

        Transferencia transferencia = new Transferencia(id, contaOrigem, contaDestino, valor, data, sucesso);

        assertEquals(id, transferencia.getId());
        assertEquals(contaOrigem, transferencia.getContaOrigem());
        assertEquals(contaDestino, transferencia.getContaDestino());
        assertEquals(valor, transferencia.getValor());
        assertEquals(data, transferencia.getData());
        assertEquals(sucesso, transferencia.getSucesso());
    }

    @Test
    public void testGettersAndSetters() {
        String id = "transferId";
        String contaOrigem = "12345";
        String contaDestino = "54321";
        Double valor = 1000.0;
        String data = "2023-10-20";
        Boolean sucesso = true;

        transferencia.setId(id);
        transferencia.setContaOrigem(contaOrigem);
        transferencia.setContaDestino(contaDestino);
        transferencia.setValor(valor);
        transferencia.setData(data);
        transferencia.setSucesso(sucesso);

        assertEquals(id, transferencia.getId());
        assertEquals(contaOrigem, transferencia.getContaOrigem());
        assertEquals(contaDestino, transferencia.getContaDestino());
        assertEquals(valor, transferencia.getValor());
        assertEquals(data, transferencia.getData());
        assertEquals(sucesso, transferencia.getSucesso());
    }

    @Test
    public void testConstructor() {
        assertNotNull(transferencia);
        assertNull(transferencia.getId());
        assertNull(transferencia.getContaOrigem());
        assertNull(transferencia.getContaDestino());
        assertNull(transferencia.getValor());
        assertNull(transferencia.getData());
        assertNull(transferencia.getSucesso());
    }

    @Test
    public void testSetId() {
        String id = "transferId";
        
        transferencia.setId(id);
        
        assertEquals(id, transferencia.getId());
    }

    @Test
    public void testSetContaOrigem() {
        String contaOrigem = "12345";
        
        transferencia.setContaOrigem(contaOrigem);
        
        assertEquals(contaOrigem, transferencia.getContaOrigem());
    }

    @Test
    public void testSetContaDestino() {
        String contaDestino = "54321";
        
        transferencia.setContaDestino(contaDestino);
        
        assertEquals(contaDestino, transferencia.getContaDestino());
    }

    @Test
    public void testSetValor() {
        Double valor = 1000.0;
        
        transferencia.setValor(valor);
        
        assertEquals(valor, transferencia.getValor());
    }

    @Test
    public void testSetData() {
        String data = "2023-10-20";
        
        transferencia.setData(data);
        
        assertEquals(data, transferencia.getData());
    }

    @Test
    public void testSetSucesso() {
        Boolean sucesso = true;
        
        transferencia.setSucesso(sucesso);
        
        assertEquals(sucesso, transferencia.getSucesso());
    }
}