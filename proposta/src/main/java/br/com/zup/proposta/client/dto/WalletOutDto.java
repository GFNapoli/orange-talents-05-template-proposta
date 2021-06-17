package br.com.zup.proposta.client.dto;

public class WalletOutDto {

	private String email;
	private String carteira;
	
	public WalletOutDto() {
	}

	public WalletOutDto(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}
	
	
}
