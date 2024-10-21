package br.com.itaucase.apitranferencia.repository;

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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.itaucase.apitranferencia.model.Cliente;

public class ClienteRepositoryTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private HashOperations<String, Object, Object> hashOperations;

    @InjectMocks
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNumeroConta("12345");

        clienteRepository.cadastrarCliente(cliente);

        verify(hashOperations, times(1)).put("Cliente", "12345", cliente);
    }

    @Test
    public void testListarClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNumeroConta("12345");

        Cliente cliente2 = new Cliente();
        cliente2.setNumeroConta("67890");

        when(hashOperations.values("Cliente")).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> clientes = clienteRepository.listarClientes();

        assertEquals(2, clientes.size(), "O número de clientes retornados está incorreto");
        assertEquals("12345", clientes.get(0).getNumeroConta(), "O número da conta do primeiro cliente está incorreto");
        assertEquals("67890", clientes.get(1).getNumeroConta(), "O número da conta do segundo cliente está incorreto");
    }

    @Test
    public void testBuscarClientePorNumeroConta() {
        Cliente cliente = new Cliente();
        cliente.setNumeroConta("12345");

        when(hashOperations.get("Cliente", "12345")).thenReturn(cliente);

        Cliente resultado = clienteRepository.buscarClientePorNumeroConta("12345");

        assertNotNull(resultado, "O cliente retornado não deve ser nulo");
        assertEquals("12345", resultado.getNumeroConta(), "O número da conta do cliente está incorreto");
    }

    @Test
    public void testBuscarClientePorNumeroContaNaoEncontrado() {
        when(hashOperations.get("Cliente", "99999")).thenReturn(null);

        Cliente resultado = clienteRepository.buscarClientePorNumeroConta("99999");

        assertNull(resultado, "O cliente não deveria ser encontrado");
    }
}