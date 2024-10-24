package br.com.itaucase.apitranferencia.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.itaucase.apitranferencia.dto.ClienteRequest;
import br.com.itaucase.apitranferencia.model.Cliente;

@Component
public class ClienteMapper {
	
	public Cliente clienteRequestParaCliente(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setId(UUID.randomUUID().toString());
        cliente.setNome(request.getNome());
        cliente.setNumeroConta(UUID.randomUUID().toString() + "-conta");
        cliente.setSaldo(request.getSaldo());
        return cliente;
	}

}
