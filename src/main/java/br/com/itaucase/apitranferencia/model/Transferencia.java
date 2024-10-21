package br.com.itaucase.apitranferencia.model;

import java.io.Serializable;

public class Transferencia implements Serializable {
	private static final long serialVersionUID = -5271302267962644559L;
	
	private String id;
    private String contaOrigem;
    private String contaDestino;
    private Double valor;
    private String data;
    private Boolean sucesso;

    public Transferencia() {}

    public Transferencia(String id, String contaOrigem, String contaDestino, Double valor, String data, Boolean sucesso) {
        this.id = id;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.data = data;
        this.sucesso = sucesso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }
}
