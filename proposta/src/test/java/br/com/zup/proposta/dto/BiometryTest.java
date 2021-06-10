package br.com.zup.proposta.dto;

import java.util.List;

public class BiometryTest {

	private List<String> biometry;

	public BiometryTest(List<String> biometry) {
		this.biometry = biometry;
	}

	public List<String> getBiometry() {
		return biometry;
	}

	public void setBiometry(List<String> biometry) {
		this.biometry = biometry;
	}
	
	
}
