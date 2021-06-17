package br.com.zup.proposta.client.dto;

public class WalletInDto {

	private String resultado;
	private String id;
	
	public WalletInDto(String resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}
	
	public WalletInDto() {
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
