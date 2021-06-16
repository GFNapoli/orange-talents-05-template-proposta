package br.com.zup.proposta.card.form;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.proposta.card.entity.Card;
import br.com.zup.proposta.card.entity.Travel;

public class TravelForm {

	@NotBlank
	private String destiny;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Future
	private LocalDate endOfTrip;

	public TravelForm() {
	}

	public String getDestiny() {
		return destiny;
	}

	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	public LocalDate getEndOfTrip() {
		return endOfTrip;
	}

	public void setEndOfTrip(LocalDate endOfTrip) {
		this.endOfTrip = endOfTrip;
	}
	
	public Travel toModel(String ipClient, String userAgent, Card card) {
		return new Travel(userAgent, endOfTrip, ipClient, userAgent, card);
	}
}
