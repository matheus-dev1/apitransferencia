package br.com.itaucase.apitranferencia.helper;

import br.com.itaucase.apitranferencia.dto.TransferenciaRequest;
import br.com.itaucase.apitranferencia.model.Transferencia;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class TransferenciaHelper {

	public Transferencia trenaferenciaRequestParaTransferencia(TransferenciaRequest transferenciaRequest) {
	    Transferencia transferencia = new Transferencia();
	    transferencia.setId(UUID.randomUUID().toString());
	    transferencia.setContaOrigem(transferenciaRequest.getContaOrigem());
	    transferencia.setContaDestino(transferenciaRequest.getContaDestino());
	    transferencia.setValor(transferenciaRequest.getValor());
	    transferencia.setData(formatarData());
	    return transferencia;
	}

	public String formatarData() {
		LocalDateTime dataAtual = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return dataAtual.format(formatter);
	}
}
