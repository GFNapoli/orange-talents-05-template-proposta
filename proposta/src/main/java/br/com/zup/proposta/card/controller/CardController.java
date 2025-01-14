package br.com.zup.proposta.card.controller;

import javax.servlet.http.HttpServletRequest;
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
import br.com.zup.proposta.card.form.TravelForm;
import br.com.zup.proposta.card.form.WalletFrom;
import br.com.zup.proposta.card.repository.CardRepository;
import br.com.zup.proposta.client.ApiCards;
import br.com.zup.proposta.client.dto.CartaoResouceInDto;
import br.com.zup.proposta.client.dto.CarteirasLegadoDto;
import br.com.zup.proposta.client.dto.TravelOutDto;
import br.com.zup.proposta.client.dto.WalletInDto;
import br.com.zup.proposta.client.dto.WalletOutDto;
import br.com.zup.proposta.client.dto.BlockingDto;
import br.com.zup.proposta.exception.ProposalRequestException;
import feign.FeignException;

@RestController
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardRepository repository;
	
	@Autowired
	private ApiCards apiCards;
	
	@PostMapping("/biometry/{cardNumber}")
	@Transactional
	public ResponseEntity<?> addBiometry(@PathVariable String cardNumber, @RequestBody @Valid BiometryForm biometry){
		
		Card card = repository.findByCardNumber(cardNumber).orElseThrow(
				() -> new ProposalRequestException("Cartão não cadastrado!", HttpStatus.NOT_FOUND));
		
		card.addBiometry(biometry);
		
		repository.save(card);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/block/{cardNumber}")
	@Transactional
	public ResponseEntity<?> blockCard(@PathVariable String cardNumber, HttpServletRequest request){
		
		CartaoResouceInDto blocking;
		Card card = repository.findByCardNumber(cardNumber).orElseThrow(
				() -> new ProposalRequestException("Cartão não cadastrado!", HttpStatus.NOT_FOUND));
		
		if(card.getBlocked()== true) throw new ProposalRequestException("Cartão já bloqueado", HttpStatus.BAD_REQUEST);
		
		try {
			blocking = apiCards.blockCard(cardNumber, new BlockingDto());
		} catch (FeignException e) {
			throw new ProposalRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		
		if(!blocking.getResultado().equalsIgnoreCase("BLOQUEADO")) 
			throw new ProposalRequestException("Erro no retorno da api cartoes, resultado enviado: "+blocking.getResultado(), null);
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) ip = request.getLocalAddr();
		
		String user = request.getHeader("User-Agent");
		
		card.blockCard(user, ip);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/travel/{cardNumber}")
	@Transactional
	public ResponseEntity<?> tripNotice(@PathVariable String cardNumber, @RequestBody @Valid TravelForm form, HttpServletRequest request){
		
		Card card = repository.findByCardNumber(cardNumber).orElseThrow(
				() -> new ProposalRequestException("Cartão não cadastrado!", HttpStatus.NOT_FOUND));
		
		TravelOutDto travelOut = new TravelOutDto(form.getDestiny(), form.getEndOfTrip());
		CartaoResouceInDto cartaoResourece;
		
		try {
			cartaoResourece = apiCards.travelNotice(cardNumber, travelOut);
		} catch (FeignException e) {
			throw new ProposalRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		
		if(!cartaoResourece.getResultado().equalsIgnoreCase("CRIADO")) 
			throw new ProposalRequestException("Resultado inesperado na requisição legado, mensagem recebida : "+cartaoResourece.getResultado(), HttpStatus.BAD_GATEWAY);
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(ip == null) ip = request.getLocalAddr();
		
		String user = request.getHeader("User-Agent");
		
		card.addTravel(form.toModel(ip, user, card));
		repository.save(card);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/wallet/{cardNumber}/a={empresa}")
	public ResponseEntity<?> conectWallet(@PathVariable String cardNumber, @PathVariable final String empresa, @RequestBody @Valid WalletFrom form){
		
		System.out.println(empresa);
		if(!(empresa.equalsIgnoreCase("PAYPAL") || empresa.equalsIgnoreCase("SANSUNGPAY"))) {
			throw new ProposalRequestException("Associação invalida, empresa não disponivel", HttpStatus.BAD_REQUEST);
		}
		
		Card card = repository.findByCardNumber(cardNumber).orElseThrow(
				() -> new ProposalRequestException("Cartão não cadastrado!", HttpStatus.NOT_FOUND));
		
		CarteirasLegadoDto carteirasLegado;
		try {
			carteirasLegado = apiCards.getWallets(cardNumber);
		} catch (FeignException e) {
			throw new ProposalRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		
		carteirasLegado.getCarteiras().forEach(wallet -> {
			if(wallet.getEmissor().equalsIgnoreCase(empresa)) {
				throw new ProposalRequestException("Carteira já associada", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		});
		
		WalletInDto walletIn;
		WalletOutDto walletOut = new WalletOutDto(form.getEmail(), empresa.toUpperCase());
		
		try {
			walletIn = apiCards.connectWallet(cardNumber, walletOut);
		} catch (FeignException e) {
			throw new ProposalRequestException(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		
		card.connectWallet(form.toModel(walletIn.getId(), card, empresa.toUpperCase()));
		repository.save(card);
		
		return ResponseEntity.ok().build();
	}
}
