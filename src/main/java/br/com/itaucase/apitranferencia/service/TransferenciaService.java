package br.com.itaucase.apitranferencia.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;
import br.com.itaucase.apitranferencia.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
    private static final String FALHA_NA_TRANSFERÊNCIA = "Falha na transferência";
	private static final String TRANSFERÊNCIA_REALIZADA_COM_SUCESSO = "Transferência realizada com sucesso.";
    
    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Transactional
    public String transferir(TransferenciaRequest transferenciaRequest) {
        Transferencia transferencia = transferenciaRepository.realizarTransferencia(transferenciaRequest);
        
        if (!transferencia.getSucesso()) {
        	return FALHA_NA_TRANSFERÊNCIA;
        }
        return TRANSFERÊNCIA_REALIZADA_COM_SUCESSO;
    }

	public List<Transferencia> buscarTransferenciasPorConta(String numeroConta) {
		return transferenciaRepository.buscarTransferenciasPorConta(numeroConta);
	}
    
}