package br.com.zup.proposta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.proposta.client.dto.ApiNewCardIn;
import br.com.zup.proposta.client.dto.ApiNewCardOut;
import br.com.zup.proposta.client.dto.BlockingDto;
import br.com.zup.proposta.client.dto.CartaoResouceInDto;
import br.com.zup.proposta.client.dto.CarteirasLegadoDto;
import br.com.zup.proposta.client.dto.TravelOutDto;
import br.com.zup.proposta.client.dto.WalletInDto;
import br.com.zup.proposta.client.dto.WalletOutDto;

@FeignClient(name = "cards", url = "${cartoes.host}")
public interface ApiCards {

	@PostMapping("/cartoes")
	ApiNewCardIn newCard(ApiNewCardOut apiNewCardOut);
	
	@PostMapping("/cartoes/{cardNumber}/bloqueios")
	CartaoResouceInDto blockCard(@PathVariable String cardNumber, BlockingDto block);
	
	@PostMapping("/cartoes/{cardNumber}/avisos")
	CartaoResouceInDto travelNotice(@PathVariable String cardNumber, TravelOutDto travelOutDto);
	
	@GetMapping("/cartoes/{cardNumber}")
	CarteirasLegadoDto getWallets(@PathVariable String cardNumber);
	
	@PostMapping("/cartoes/{cardNumber}/carteiras")
	WalletInDto connectWallet(@PathVariable String cardNumber, WalletOutDto walletDto);
}
