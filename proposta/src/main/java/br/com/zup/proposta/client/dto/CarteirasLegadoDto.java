package br.com.zup.proposta.client.dto;

import java.util.List;

public class CarteirasLegadoDto {

	private List<CarteiraDto> carteiras;

	public CarteirasLegadoDto() {
	}

	public List<CarteiraDto> getCarteiras() {
		return carteiras;
	}

	public void setCarteiras(List<CarteiraDto> carteiras) {
		this.carteiras = carteiras;
	}
	
}
