package br.com.zup.proposta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.proposta.client.dto.ApiNewCardIn;
import br.com.zup.proposta.client.dto.ApiNewCardOut;
import br.com.zup.proposta.client.dto.BlockingDto;
import br.com.zup.proposta.client.dto.CartaoResouceInDto;
import br.com.zup.proposta.client.dto.TravelOutDto;

@FeignClient(name = "cards", url = "${cartoes.host}")
public interface ApiCards {

	@PostMapping("/cartoes")
	ApiNewCardIn newCard(ApiNewCardOut apiNewCardOut);
	
	@PostMapping("/cartoes/{cardNumber}/bloqueios")
	CartaoResouceInDto blockCard(@PathVariable String cardNumber, BlockingDto block);
	
	@PostMapping("/cartoes/{cardNumber}/avisos")
	CartaoResouceInDto travelNotice(@PathVariable String cardNumber, TravelOutDto travelOutDto);
}
