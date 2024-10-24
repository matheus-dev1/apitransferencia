package br.com.itaucase.apitranferencia.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.itaucase.apitranferencia.mapper.ClienteMapper;
import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.service.ClienteService;

@WebMvcTest(ClienteController.class)
public class ClienteControllerIntegracaoTest {
	
	@Autowired
	private MockMvc mockMvc;
    
    @MockBean
    private ClienteService clienteService;
    
    @MockBean
    private ClienteMapper clienteHelper;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCadastrarCliente() throws Exception {
        mockMvc.perform(post("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"nome\":\"João\",\"numeroConta\":\"123456\",\"saldo\":1000.0}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Cliente cadastrado com sucesso."));
    }

    @Test
    void testListarClientes() throws Exception {
        Cliente cliente1 = new Cliente("1", "João", "123456", 1000.0);
        Cliente cliente2 = new Cliente("2", "Maria", "654321", 2000.0);
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(clienteService.listarClientes()).thenReturn(clientes);

        mockMvc.perform(get("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"nome\":\"João\",\"numeroConta\":\"123456\",\"saldo\":1000.0}," +
                        "{\"id\":\"2\",\"nome\":\"Maria\",\"numeroConta\":\"654321\",\"saldo\":2000.0}]"));
    }

    @Test
    void testBuscarClientePorNumeroConta() throws Exception {
        Cliente cliente = new Cliente("1", "João", "123456", 1000.0);

        when(clienteService.buscarClientePorNumeroConta("123456")).thenReturn(cliente);

        mockMvc.perform(get("/api/v1/clientes/numeroConta/123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"nome\":\"João\",\"numeroConta\":\"123456\",\"saldo\":1000.0}"));
    }

    @Test
    void testBuscarClientePorNumeroContaNotFound() throws Exception {
        when(clienteService.buscarClientePorNumeroConta("000000")).thenReturn(null);

        mockMvc.perform(get("/api/v1/clientes/numeroConta/000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}