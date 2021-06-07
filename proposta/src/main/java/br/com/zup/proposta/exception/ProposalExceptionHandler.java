package br.com.zup.proposta.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProposalExceptionHandler {

	@ExceptionHandler(value = ProposalRequestException.class)
	public ResponseEntity<Object> proposalHandlerException(ProposalRequestException e){
		
		ProposalException exception = new ProposalException(e.getMessage(), e.getHttpStatus());
		
		return new ResponseEntity<>(exception, e.getHttpStatus());
	}
}
