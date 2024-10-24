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

import br.com.itaucase.apitranferencia.dto.ClienteRequest;
import br.com.itaucase.apitranferencia.mapper.ClienteMapper;
import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private static final String CLIENTE_CADASTRADO_COM_SUCESSO = "Cliente cadastrado com sucesso.";
    
	private final ClienteService clienteService;
    private final ClienteMapper clienteMapper;

    public ClienteController(
    		ClienteService clienteService,
    		ClienteMapper clienteHelper
    	) {
        	this.clienteService = clienteService;
        	this.clienteMapper = clienteHelper;
    }

    @Operation(summary = "Cadastrar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente Cadastrado com sucesso")
        })
    @PostMapping
    public ResponseEntity<String> cadastrarCliente(@RequestBody ClienteRequest request) {
        Cliente cliente = clienteMapper.clienteRequestParaCliente(request);
        clienteService.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(CLIENTE_CADASTRADO_COM_SUCESSO);
    }

    @Operation(summary = "Listar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes recuperada com sucesso")
        })
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Buscar cliente por número da conta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/numeroConta/{numeroConta}")
    public ResponseEntity<Cliente> buscarClientePorNumeroConta(@PathVariable String numeroConta) {
        Cliente cliente = clienteService.buscarClientePorNumeroConta(numeroConta);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}