package br.com.itaucase.apitranferencia.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.service.TransferenciaService;

@WebMvcTest(TransferenciaController.class)
class TransferenciaControllerIntegracaoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferenciaService transferenciaService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testTransferir() throws Exception {
        when(transferenciaService.transferir(any(TransferenciaRequest.class)))
                .thenReturn("Transferência realizada com sucesso.");

        TransferenciaRequest transferenciaRequest = new TransferenciaRequest("123456", "654321", 200.0);

        mockMvc.perform(post("/api/v1/transferencias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transferenciaRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("Transferência realizada com sucesso."));
    }
    
    @Test
    void testBuscarTransferenciasPorConta() throws Exception {
        List<Transferencia> transferencias = List.of(
            new Transferencia("1", "123456", "654321", 150.0, "2024-10-20", true)
        );
        
        when(transferenciaService.buscarTransferenciasPorConta("123456")).thenReturn(transferencias);

        mockMvc.perform(get("/api/v1/transferencias/123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contaOrigem").value("123456"));
    }
}