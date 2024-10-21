package br.com.itaucase.apitranferencia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.itaucase.apitranferencia.dto.ClienteRequest;
import br.com.itaucase.apitranferencia.helper.ClienteHelper;
import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.service.ClienteService;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteHelper clienteHelper;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarCliente() {
        ClienteRequest clienteRequest = new ClienteRequest();
        clienteRequest.setNome("Cliente Teste");
        clienteRequest.setSaldo(1000.0);

        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setSaldo(clienteRequest.getSaldo());

        when(clienteHelper.clienteRequestParaCliente(clienteRequest)).thenReturn(cliente);

        ResponseEntity<String> response = clienteController.cadastrarCliente(clienteRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Cliente cadastrado com sucesso.", response.getBody());
        verify(clienteService).cadastrarCliente(cliente);
    }

    @Test
    public void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        clientes.add(new Cliente());

        when(clienteService.listarClientes()).thenReturn(clientes);

        ResponseEntity<List<Cliente>> response = clienteController.listarClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    public void testBuscarClientePorNumeroContaExistente() {
        String numeroConta = "12345";
        Cliente cliente = new Cliente();
        cliente.setNumeroConta(numeroConta);

        when(clienteService.buscarClientePorNumeroConta(numeroConta)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.buscarClientePorNumeroConta(numeroConta);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    public void testBuscarClientePorNumeroContaNaoExistente() {
        String numeroConta = "54321";

        when(clienteService.buscarClientePorNumeroConta(numeroConta)).thenReturn(null);

        ResponseEntity<Cliente> response = clienteController.buscarClientePorNumeroConta(numeroConta);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}