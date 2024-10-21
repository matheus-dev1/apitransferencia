package br.com.itaucase.apitranferencia.dto;

public class ClienteRequest {
    private String id;
    private String nome;
    private String numeroConta;
    private Double saldo;
    
	public ClienteRequest() {}
	
	public ClienteRequest(String id, String nome, String numeroConta, Double saldo) {
		super();
		this.id = id;
		this.nome = nome;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}