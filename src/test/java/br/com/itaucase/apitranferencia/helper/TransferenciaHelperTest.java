package br.com.itaucase.apitranferencia.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;

public class TransferenciaHelperTest {

    private TransferenciaHelper transferenciaHelper;

    @BeforeEach
    public void setUp() {
        transferenciaHelper = new TransferenciaHelper();
    }

    @Test
    public void testTrenaferenciaRequestParaTransferencia() {
        TransferenciaRequest request = new TransferenciaRequest();
        request.setContaOrigem("123456");
        request.setContaDestino("654321");
        request.setValor(200.0);

        Transferencia transferencia = transferenciaHelper.trenaferenciaRequestParaTransferencia(request);

        assertNotNull(transferencia.getId(), "O ID da transferência não deve ser nulo");
        assertEquals("123456", transferencia.getContaOrigem(), "A conta de origem está incorreta");
        assertEquals("654321", transferencia.getContaDestino(), "A conta de destino está incorreta");
        assertEquals(200.0, transferencia.getValor(), "O valor da transferência está incorreto");
        assertNotNull(transferencia.getData(), "A data da transferência não deve ser nula");
        assertTrue(transferencia.getData().matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}"), "O formato da data está incorreto");
    }

    @Test
    public void testTrenaferenciaRequestParaTransferenciaComValorZero() {
        TransferenciaRequest request = new TransferenciaRequest();
        request.setContaOrigem("123456");
        request.setContaDestino("654321");
        request.setValor(0.0);

        Transferencia transferencia = transferenciaHelper.trenaferenciaRequestParaTransferencia(request);

        assertNotNull(transferencia.getId(), "O ID da transferência não deve ser nulo");
        assertEquals(0.0, transferencia.getValor(), "O valor da transferência deve ser zero");
    }
}