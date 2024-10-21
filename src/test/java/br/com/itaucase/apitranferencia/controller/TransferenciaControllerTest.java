package br.com.itaucase.apitranferencia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.service.TransferenciaService;

public class TransferenciaControllerTest {

    @Mock
    private TransferenciaService transferenciaService;

    @InjectMocks
    private TransferenciaController transferenciaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransferirSucesso() {
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setContaOrigem("12345");
        transferenciaRequest.setContaDestino("54321");
        transferenciaRequest.setValor(1000.0);

        String expectedResponse = "Transferência realizada com sucesso.";

        when(transferenciaService.transferir(transferenciaRequest)).thenReturn(expectedResponse);

        ResponseEntity<String> response = transferenciaController.transferir(transferenciaRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testBuscarTransferenciasPorConta() {
        String numeroConta = "12345";
        List<Transferencia> transferencias = new ArrayList<>();
        transferencias.add(new Transferencia());
        transferencias.add(new Transferencia());

        when(transferenciaService.buscarTransferenciasPorConta(numeroConta)).thenReturn(transferencias);

        ResponseEntity<List<Transferencia>> response = transferenciaController.buscarTransferenciasPorConta(numeroConta);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transferencias, response.getBody());
    }
}