package br.com.itaucase.apitranferencia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.repository.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.cadastrarCliente(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.listarClientes();
    }

    public Cliente buscarClientePorNumeroConta(String numeroConta) {
        return clienteRepository.buscarClientePorNumeroConta(numeroConta);
    }
}