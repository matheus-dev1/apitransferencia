package br.com.itaucase.apitranferencia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/transferencias")
public class TransferenciaController {
    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @Operation(summary = "Transferir um valor de uma conta para outra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferência realizada com sucesso.")
        })
    @PostMapping
    public ResponseEntity<String> transferir(@RequestBody TransferenciaRequest transferenciaRequest) {
        String result = transferenciaService.transferir(transferenciaRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    
    @Operation(summary = "Buscar histórico de transferencais de uma conta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historico de transações retornada com sucesso")
        })
    @GetMapping("/{numeroConta}")
    public ResponseEntity<List<Transferencia>> buscarTransferenciasPorConta(@PathVariable String numeroConta) {
        List<Transferencia> transferencias = transferenciaService.buscarTransferenciasPorConta(numeroConta);
        return ResponseEntity.ok(transferencias);
    }
}