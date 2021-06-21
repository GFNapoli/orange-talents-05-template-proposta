package br.com.zup.proposta.config.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptDocument {

	private String document;

	public EncryptDocument(String document) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
		this.document = encoder.encode(document);
	}

	public String getDocument() {
		return document;
	}
	
}
