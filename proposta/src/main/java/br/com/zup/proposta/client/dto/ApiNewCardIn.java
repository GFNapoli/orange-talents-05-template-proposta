package br.com.zup.proposta.client.dto;

public class ApiNewCardIn {

	private String id;

	public ApiNewCardIn(String idCard) {
		this.id = idCard;
	}

	public ApiNewCardIn() {
	}

	public String getIdCard() {
		return id;
	}

	public void setId(String idCard) {
		this.id = idCard;
	}
}
