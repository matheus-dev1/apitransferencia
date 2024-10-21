package br.com.itaucase.apitranferencia.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.helper.TransferenciaHelper;
import br.com.itaucase.apitranferencia.model.Cliente;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.service.ClienteService;

@Repository
public class TransferenciaRepository {
    private static final String REDIS_KEY_TRANSFERENCIA = "Transferencia";
    
    private ClienteService clienteService;
    private TransferenciaHelper transferenciaHelper;
    private final RedisTemplate<String, Object> redisTemplate;

    public TransferenciaRepository(
    		ClienteService clienteService, 
    		TransferenciaHelper transferenciaHelper,
    		RedisTemplate<String, Object> redisTemplate
    	) {
    	this.clienteService = clienteService;
    	this.transferenciaHelper = transferenciaHelper;
        this.redisTemplate = redisTemplate;
    }

    public Transferencia realizarTransferencia(TransferenciaRequest transferenciaRequest) {
    	HashOperations<String, String, Transferencia> hashOperations = redisTemplate.opsForHash();
    	
        Cliente origem = clienteService.buscarClientePorNumeroConta(transferenciaRequest.getContaOrigem());
        Cliente destino = clienteService.buscarClientePorNumeroConta(transferenciaRequest.getContaDestino());
        Double valor = transferenciaRequest.getValor();

        Transferencia transferencia = transferenciaHelper.trenaferenciaRequestParaTransferencia(transferenciaRequest);

        if (origem.getSaldo() >= valor && valor <= 10000) {
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            transferencia.setSucesso(true);

            clienteService.cadastrarCliente(origem);  // Atualiza o cliente de origem
            clienteService.cadastrarCliente(destino); // Atualiza o cliente de destino
        } else {
            transferencia.setSucesso(false);
        }

        // Metodo put, cria ou atualiza um hash
        hashOperations.put(REDIS_KEY_TRANSFERENCIA, transferencia.getId(), transferencia);
        
        return transferencia;
    }

    public List<Transferencia> buscarTransferenciasPorConta(String numeroConta) {
    	HashOperations<String, String, Transferencia> hashOperations = redisTemplate.opsForHash();
    	
    	// metodo values, retorna todos os dados da chave
        return hashOperations.values(REDIS_KEY_TRANSFERENCIA)
                .stream()
                .map(object -> (Transferencia) object)
                .filter(
                		tranferencia -> 
                			tranferencia.getContaOrigem().equals(numeroConta) || 
                			tranferencia.getContaDestino().equals(numeroConta)
                		)
                .sorted(
                		(transfernciaComparada, transferenciaComparadora) -> 
                		transferenciaComparadora.getData().compareTo(transfernciaComparada.getData())
                		)
                .collect(Collectors.toList());
    }
}
