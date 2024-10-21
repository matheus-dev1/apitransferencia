package br.com.itaucase.apitranferencia.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import br.com.itaucase.apitranferencia.model.Cliente;

@Repository
public class ClienteRepository {
	
	private static final String REDIS_KEY_CLIENTE = "Cliente";
	
    private final RedisTemplate<String, Object> redisTemplate;

    public ClienteRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cadastrarCliente(Cliente cliente) {
    	HashOperations<String, String, Cliente> hashOperations = redisTemplate.opsForHash();
    	// Metodo put, cria ou atualiza um hash
    	hashOperations.put(REDIS_KEY_CLIENTE, cliente.getNumeroConta(), cliente);
    }

    public List<Cliente> listarClientes() {
    	HashOperations<String, String, Cliente> hashOperations = redisTemplate.opsForHash();
    	// metodo values, retorna todos os dados da chave
    	return hashOperations.values(REDIS_KEY_CLIENTE)
    			.stream()
    			.map(object -> (Cliente) object)
    			.collect(Collectors.toList());       
    }

    public Cliente buscarClientePorNumeroConta(String numeroConta) {
    	HashOperations<String, String, Cliente> hashOperations = redisTemplate.opsForHash();
    	/*
    	 * O método get, recupera um valor especifico de um hash (Cliente) utilizando a chave interna (numeroConta)
		   Ex: Hash "Clientes":
  				Chave: "12345" -> Valor: cliente (id, nome, numeroConta, saldo)
    	 */
    	return (Cliente) hashOperations.get(REDIS_KEY_CLIENTE, numeroConta);
    }
}
