package br.com.zup.proposta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.proposta.client.dto.ApiNewCardIn;
import br.com.zup.proposta.client.dto.ApiNewCardOut;

@FeignClient(name = "cards", url = "${cartoes.host}")
public interface ApiCards {

	@PostMapping("/cartoes")
	ApiNewCardIn newCard(ApiNewCardOut apiNewCardOut);
}
