package br.com.itaucase.apitranferencia.dto;

public class TransferenciaRequest {
    private String contaOrigem;
    private String contaDestino;
    private Double valor;
    
	public TransferenciaRequest() {}
	
	public TransferenciaRequest(String contaOrigem, String contaDestino, Double valor) {
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
	}
	public String getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
