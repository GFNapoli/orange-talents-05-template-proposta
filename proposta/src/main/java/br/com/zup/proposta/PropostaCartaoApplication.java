package br.com.zup.proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@EnableScheduling
public class PropostaCartaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaCartaoApplication.class, args);
	}

}
