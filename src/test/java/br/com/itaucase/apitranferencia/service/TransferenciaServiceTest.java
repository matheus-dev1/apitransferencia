package br.com.itaucase.apitranferencia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.repository.TransferenciaRepository;

public class TransferenciaServiceTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private TransferenciaService transferenciaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransferirSucesso() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigem("12345");
        transferenciaRequest.setContaDestino("67890");
        transferenciaRequest.setValor(1000.0);

        Transferencia transferencia = new Transferencia();
        transferencia.setSucesso(true);

        when(transferenciaRepository.realizarTransferencia(transferenciaRequest)).thenReturn(transferencia);

        String resultado = transferenciaService.transferir(transferenciaRequest);

        assertEquals("Transferência realizada com sucesso.", resultado);
    }

    @Test
    public void testTransferirFalha() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigem("12345");
        transferenciaRequest.setContaDestino("67890");
        transferenciaRequest.setValor(1000.0);

        Transferencia transferencia = new Transferencia();
        transferencia.setSucesso(false);

        when(transferenciaRepository.realizarTransferencia(transferenciaRequest)).thenReturn(transferencia);

        String resultado = transferenciaService.transferir(transferenciaRequest);

        assertEquals("Falha na transferência", resultado);
    }

    @Test
    public void testBuscarTransferenciasPorConta() {
        String numeroConta = "12345";
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setContaOrigem(numeroConta);
        transferencia1.setContaDestino("67890");
        
        Transferencia transferencia2 = new Transferencia();
        transferencia2.setContaOrigem("54321");
        transferencia2.setContaDestino(numeroConta);

        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(transferencia1);
        transferencias.add(transferencia2);

        when(transferenciaRepository.buscarTransferenciasPorConta(numeroConta)).thenReturn(transferencias);

        List<Transferencia> resultados = transferenciaService.buscarTransferenciasPorConta(numeroConta);

        assertEquals(2, resultados.size(), "Deve retornar duas transferências");
    }
}