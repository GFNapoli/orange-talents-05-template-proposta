package br.com.zup.proposta.exception;

import org.springframework.http.HttpStatus;

public class ProposalException {

	private final String message;
	private final HttpStatus status;
	
	public ProposalException(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
}
