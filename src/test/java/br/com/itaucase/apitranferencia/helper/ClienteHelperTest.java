package br.com.itaucase.apitranferencia.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.itaucase.apitranferencia.dto.ClienteRequest;
import br.com.itaucase.apitranferencia.mapper.ClienteMapper;
import br.com.itaucase.apitranferencia.model.Cliente;

public class ClienteHelperTest {

    private ClienteMapper clienteMapper;

    @BeforeEach
    public void setUp() {
        clienteMapper = new ClienteMapper();
    }

    @Test
    public void testClienteRequestParaCliente() {
        ClienteRequest request = new ClienteRequest();
        request.setNome("João");
        request.setSaldo(1000.0);

        Cliente cliente = clienteMapper.clienteRequestParaCliente(request);

        assertNotNull(cliente.getId(), "O ID do cliente não deve ser nulo");
        assertEquals("João", cliente.getNome(), "O nome do cliente está incorreto");
        assertNotNull(cliente.getNumeroConta(), "O número da conta não deve ser nulo");
        assertTrue(cliente.getNumeroConta().endsWith("-conta"), "O número da conta deve terminar com '-conta'");
        assertEquals(1000.0, cliente.getSaldo(), "O saldo do cliente está incorreto");
    }

    @Test
    public void testClienteRequestParaClienteComSaldoNegativo() {
        ClienteRequest request = new ClienteRequest();
        request.setNome("Maria");
        request.setSaldo(-500.0);

        Cliente cliente = clienteMapper.clienteRequestParaCliente(request);

        assertNotNull(cliente.getId(), "O ID do cliente não deve ser nulo");
        assertEquals("Maria", cliente.getNome(), "O nome do cliente está incorreto");
        assertNotNull(cliente.getNumeroConta(), "O número da conta não deve ser nulo");
        assertTrue(cliente.getNumeroConta().endsWith("-conta"), "O número da conta deve terminar com '-conta'");
        assertEquals(-500.0, cliente.getSaldo(), "O saldo do cliente está incorreto");
    }
}