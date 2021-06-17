package br.com.zup.proposta.card.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.zup.proposta.card.entity.Card;
import br.com.zup.proposta.card.entity.Wallet;

public class WalletFrom {

	@NotBlank
	@Email
	private String email;
	
	public WalletFrom() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Wallet toModel(String id, Card card, String string) {
		return new Wallet(email, string, card, id);
	}
	
}
