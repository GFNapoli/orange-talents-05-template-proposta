package br.com.zup.proposta.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.proposta.client.dto.SolicitationIn;
import br.com.zup.proposta.client.dto.SolicitationOut;

@FeignClient(name = "solicitation", url = "${solicitacao.host}")
public interface Solicitation {

	@PostMapping("/solicitacao")
	SolicitationIn approval(SolicitationOut solicitation);
}
