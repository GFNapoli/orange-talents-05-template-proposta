package br.com.zup.proposta.exception;

public class ErrorFormDto {

	private String field;
	private String error;
	
	public ErrorFormDto(String field, String message) {
		this.field = field;
		this.error = message;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
}
