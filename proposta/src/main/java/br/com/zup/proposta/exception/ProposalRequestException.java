package br.com.zup.proposta.exception;

import org.springframework.http.HttpStatus;

public class ProposalRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;

	public ProposalRequestException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public ProposalRequestException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
		
}
