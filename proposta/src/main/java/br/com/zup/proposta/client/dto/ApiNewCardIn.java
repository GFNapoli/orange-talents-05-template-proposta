package br.com.zup.proposta.client.dto;

import br.com.zup.proposta.card.entity.Card;
import br.com.zup.proposta.proposals.entity.Proposal;

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
	
	public Card tomodel(Proposal proposal) {
		return new Card(id, proposal);
	}
}
