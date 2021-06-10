package br.com.zup.proposta.card.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.proposta.card.entity.Card;
import br.com.zup.proposta.card.form.BiometryForm;
import br.com.zup.proposta.card.repository.CardRepository;
import br.com.zup.proposta.exception.ProposalRequestException;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardRepository repository;
	
	@PostMapping("/biometry/{cardNumber}")
	@Transactional
	public ResponseEntity<?> addBiometry(@PathVariable String cardNumber, @RequestBody @Valid BiometryForm biometry){
		
		Card card = repository.findByCardNumber(cardNumber).orElseThrow(
				() -> new ProposalRequestException("Cartão não cadastrado!", HttpStatus.NOT_FOUND));
		
		card.addBiometry(biometry);
		
		repository.save(card);
		return ResponseEntity.ok().build();
	}
}
