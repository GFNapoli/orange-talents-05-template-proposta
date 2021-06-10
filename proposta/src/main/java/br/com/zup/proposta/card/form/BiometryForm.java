package br.com.zup.proposta.card.form;

import java.util.List;

import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

public class BiometryForm {

	@Size(min = 1)
	@NotNull
	private List<String> biometry;

	public List<String> getBiometry() {
		return biometry;
	}

	public void setBiometry(List<String> biometry) {
		this.biometry = biometry;
	}
	
}
