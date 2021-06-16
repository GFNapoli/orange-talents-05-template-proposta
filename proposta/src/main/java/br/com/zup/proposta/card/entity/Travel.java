package br.com.zup.proposta.card.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
public class Travel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String destiny;
	
	@Future
	@NotNull
	private LocalDate endOfTrip;
	private LocalDateTime tripNotice;
	private String ipClient;
	private String userAgent;
	
	@ManyToOne
	@NotNull
	private Card card;
	
	public Travel() {
	}

	public Travel(@NotBlank String destiny, @Future LocalDate endOfTrip, String ipClient,
			String userAgent, Card card) {
		this.destiny = destiny;
		this.endOfTrip = endOfTrip;
		this.tripNotice = LocalDateTime.now();
		this.ipClient = ipClient;
		this.userAgent = userAgent;
		this.card = card;
	}

	public Long getId() {
		return id;
	}

	public String getDestiny() {
		return destiny;
	}

	public LocalDate getEndOfTrip() {
		return endOfTrip;
	}

	public LocalDateTime getTripNotice() {
		return tripNotice;
	}

	public String getIpClient() {
		return ipClient;
	}

	public String getUserAgent() {
		return userAgent;
	}
}
