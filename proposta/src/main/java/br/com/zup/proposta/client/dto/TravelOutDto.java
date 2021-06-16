package br.com.zup.proposta.client.dto;

import java.time.LocalDate;

public class TravelOutDto {

	private String destino;
	private LocalDate validoAte;
	
	public TravelOutDto(String destino, LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public TravelOutDto() {
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}
	
}
