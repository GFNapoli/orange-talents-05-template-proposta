package br.com.zup.proposta.card.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.zup.proposta.card.form.BiometryForm;
import br.com.zup.proposta.proposals.entity.Proposal;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "card", cascade = CascadeType.MERGE)
	private List<Biometry> biometry = new ArrayList<Biometry>();
	
	@OneToOne
	private Proposal proposal;
	
	@NotBlank
	@NotNull
	private String cardNumber;
	
	public Card() {
	}

	public Card(String cardNumber, Proposal proposal) {
		this.cardNumber = cardNumber;
		this.proposal = proposal;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Long getId() {
		return id;
	}

	public List<Biometry> getBiometry() {
		return biometry;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void addBiometry(BiometryForm biometryForm) {
		this.biometry.addAll(biometryForm.getBiometry()
				.stream().map(bio -> new Biometry(bio, this)).collect(Collectors.toList()));
	}
}