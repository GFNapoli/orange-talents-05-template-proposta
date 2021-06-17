package br.com.zup.proposta.card.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Email
	private String email;
	private String wallet;
	private LocalDateTime associatedIn = LocalDateTime.now();
	private String idLegado;
	
	@ManyToOne
	private Card card;
	
	public Wallet() {
	}

	public Wallet(@NotBlank String email, String wallet, Card card, String idLegado) {
		this.email = email;
		this.wallet = wallet;
		this.card = card;
		this.idLegado = idLegado;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getWallet() {
		return wallet;
	}

	public LocalDateTime getAssociatedIn() {
		return associatedIn;
	}

	public Card getCard() {
		return card;
	}

	public String getIdLegado() {
		return idLegado;
	}
	
	
}
