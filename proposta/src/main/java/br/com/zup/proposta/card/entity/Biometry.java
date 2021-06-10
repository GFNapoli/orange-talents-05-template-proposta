package br.com.zup.proposta.card.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Biometry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@NotNull
	private String biometry;
	private LocalDateTime creationdate = LocalDateTime.now();
	
	@NotNull
	@ManyToOne
	private Card card;
	
	public Biometry() {
	}

	public Biometry(@NotBlank String biometry, Card card) {
		this.biometry = biometry;
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public String getBiometry() {
		return biometry;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}
	
}
