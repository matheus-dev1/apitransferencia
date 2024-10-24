package br.com.itaucase.apitranferencia.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.mapper.TransferenciaMapper;
import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.service.ClienteService;

public class TransferenciaRepositoryTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private TransferenciaMapper transferenciaMapper;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private HashOperations<String, Object, Object> hashOperations;

    @InjectMocks
    private TransferenciaRepository transferenciaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
    }

    @Test
    public void testRealizarTransferenciaSucesso() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigem("12345");
        transferenciaRequest.setContaDestino("54321");
        transferenciaRequest.setValor(5000.0);

        Cliente origem = new Cliente();
        origem.setNumeroConta("12345");
        origem.setSaldo(10000.0);

        Cliente destino = new Cliente();
        destino.setNumeroConta("54321");
        destino.setSaldo(2000.0);

        Transferencia transferencia = new Transferencia();
        transferencia.setId("transferId");
        transferencia.setContaOrigem("12345");
        transferencia.setContaDestino("54321");
        transferencia.setValor(5000.0);
        transferencia.setSucesso(true);

        when(clienteService.buscarClientePorNumeroConta("12345")).thenReturn(origem);
        when(clienteService.buscarClientePorNumeroConta("54321")).thenReturn(destino);
        when(transferenciaMapper.trenaferenciaRequestParaTransferencia(transferenciaRequest)).thenReturn(transferencia);

        Transferencia resultado = transferenciaRepository.realizarTransferencia(transferenciaRequest);

        assertTrue(resultado.getSucesso());
        assertEquals(5000.0, origem.getSaldo());
        assertEquals(7000.0, destino.getSaldo());
        verify(hashOperations).put("Transferencia", transferencia.getId(), transferencia);
        verify(clienteService).cadastrarCliente(origem);
        verify(clienteService).cadastrarCliente(destino);
    }

    @Test
    public void testRealizarTransferenciaFalhaSaldoInsuficiente() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigem("12345");
        transferenciaRequest.setContaDestino("54321");
        transferenciaRequest.setValor(15000.0);

        Cliente origem = new Cliente();
        origem.setNumeroConta("12345");
        origem.setSaldo(10000.0);

        Cliente destino = new Cliente();
        destino.setNumeroConta("54321");
        destino.setSaldo(2000.0);

        Transferencia transferencia = new Transferencia();
        transferencia.setId("transferId");
        transferencia.setContaOrigem("12345");
        transferencia.setContaDestino("54321");
        transferencia.setValor(15000.0);
        transferencia.setSucesso(false);

        when(clienteService.buscarClientePorNumeroConta("12345")).thenReturn(origem);
        when(clienteService.buscarClientePorNumeroConta("54321")).thenReturn(destino);
        when(transferenciaMapper.trenaferenciaRequestParaTransferencia(transferenciaRequest)).thenReturn(transferencia);

        Transferencia resultado = transferenciaRepository.realizarTransferencia(transferenciaRequest);

        assertFalse(resultado.getSucesso());
        assertEquals(10000.0, origem.getSaldo());
        assertEquals(2000.0, destino.getSaldo());
        verify(hashOperations).put("Transferencia", transferencia.getId(), transferencia);
        verify(clienteService, never()).cadastrarCliente(origem);
        verify(clienteService, never()).cadastrarCliente(destino);
    }

    @Test
    public void testRealizarTransferenciaFalhaValorExcedeLimite() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigem("12345");
        transferenciaRequest.setContaDestino("54321");
        transferenciaRequest.setValor(15000.0);

        Cliente origem = new Cliente();
        origem.setNumeroConta("12345");
        origem.setSaldo(20000.0);

        Cliente destino = new Cliente();
        destino.setNumeroConta("54321");
        destino.setSaldo(2000.0);

        Transferencia transferencia = new Transferencia();
        transferencia.setId("transferId");
        transferencia.setContaOrigem("12345");
        transferencia.setContaDestino("54321");
        transferencia.setValor(15000.0);
        transferencia.setSucesso(false);

        when(clienteService.buscarClientePorNumeroConta("12345")).thenReturn(origem);
        when(clienteService.buscarClientePorNumeroConta("54321")).thenReturn(destino);
        when(transferenciaMapper.trenaferenciaRequestParaTransferencia(transferenciaRequest)).thenReturn(transferencia);

        Transferencia resultado = transferenciaRepository.realizarTransferencia(transferenciaRequest);

        assertFalse(resultado.getSucesso());
        assertEquals(20000.0, origem.getSaldo());
        assertEquals(2000.0, destino.getSaldo());
        verify(hashOperations).put("Transferencia", transferencia.getId(), transferencia);
        verify(clienteService, never()).cadastrarCliente(origem);
        verify(clienteService, never()).cadastrarCliente(destino);
    }

    @Test
    public void testBuscarTransferenciasPorConta() {
        String numeroConta = "12345";
        List<Object> transferencias = new ArrayList<>();
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setContaOrigem("12345");
        transferencia1.setContaDestino("54321");
        transferencia1.setData("10-10-2024 12:12");
        transferencias.add(transferencia1);

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setContaOrigem("67890");
        transferencia2.setContaDestino("12345");
        transferencia2.setData("10-10-2024 12:12");
        transferencias.add(transferencia2);

        when(hashOperations.values("Transferencia")).thenReturn(transferencias);

        List<Transferencia> resultado = transferenciaRepository.buscarTransferenciasPorConta(numeroConta);

        assertEquals(2, resultado.size());
        assertTrue(resultado.stream().anyMatch(t -> t.getContaOrigem().equals("12345") || t.getContaDestino().equals("12345")));
    }

    @Test
    public void testBuscarTransferenciasPorContaSemTransferencias() {
        String numeroConta = "12345";
        List<Object> transferencias = new ArrayList<>();

        when(hashOperations.values("Transferencia")).thenReturn(transferencias);

        List<Transferencia> resultado = transferenciaRepository.buscarTransferenciasPorConta(numeroConta);

        assertTrue(resultado.isEmpty());
    }

    @Test
    public void testBuscarTransferenciasPorContaComUmaTransferencia() {
        String numeroConta = "12345";
        List<Object> transferencias = new ArrayList<>();
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem("12345");
        transferencia.setContaDestino("54321");
        transferencias.add(transferencia);

        when(hashOperations.values("Transferencia")).thenReturn(transferencias);

        List<Transferencia> resultado = transferenciaRepository.buscarTransferenciasPorConta(numeroConta);

        assertEquals(1, resultado.size());
        assertEquals("12345", resultado.get(0).getContaOrigem());
        assertEquals("54321", resultado.get(0).getContaDestino());
    }
}