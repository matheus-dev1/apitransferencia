package br.com.itaucase.apitranferencia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.repository.ClienteRepository;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setNumeroConta("12345");
        cliente.setSaldo(1000.0);

        clienteService.cadastrarCliente(cliente);

        verify(clienteRepository, times(1)).cadastrarCliente(cliente);
    }

    @Test
    public void testListarClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João");
        cliente1.setNumeroConta("12345");
        cliente1.setSaldo(1000.0);
        
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria");
        cliente2.setNumeroConta("67890");
        cliente2.setSaldo(2000.0);
        
        when(clienteRepository.listarClientes()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteService.listarClientes();

        assertEquals(2, clientes.size(), "Deve retornar dois clientes");
        assertEquals("João", clientes.get(0).getNome(), "O primeiro cliente deve ser João");
        assertEquals("Maria", clientes.get(1).getNome(), "O segundo cliente deve ser Maria");
    }

    @Test
    public void testBuscarClientePorNumeroConta() {
        String numeroConta = "12345";
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setNumeroConta(numeroConta);
        cliente.setSaldo(1000.0);
        
        when(clienteRepository.buscarClientePorNumeroConta(numeroConta)).thenReturn(cliente);

        Cliente resultado = clienteService.buscarClientePorNumeroConta(numeroConta);

        assertNotNull(resultado, "O cliente não deve ser nulo");
        assertEquals("João", resultado.getNome(), "O nome do cliente deve ser João");
        assertEquals(numeroConta, resultado.getNumeroConta(), "O número da conta deve ser " + numeroConta);
    }

    @Test
    public void testBuscarClientePorNumeroContaInexistente() {
        String numeroConta = "99999";
        
        when(clienteRepository.buscarClientePorNumeroConta(numeroConta)).thenReturn(null);

        Cliente resultado = clienteService.buscarClientePorNumeroConta(numeroConta);

        assertNull(resultado, "O cliente deve ser nulo para uma conta inexistente");
    }
}