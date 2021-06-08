package br.com.zup.proposta.proposal.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zup.proposta.client.ApiCards;
import br.com.zup.proposta.client.dto.ApiNewCardIn;
import br.com.zup.proposta.client.dto.ApiNewCardOut;
import br.com.zup.proposta.dto.ProposalRequestTest;
import br.com.zup.proposta.proposals.entity.Proposal;
import br.com.zup.proposta.proposals.repository.ProposalRepository;
import br.com.zup.proposta.proposals.status.StatusProposal;
import feign.FeignException;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
public class ProposalAsync {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonMaper;
	
	@Autowired
	EntityManager manager;
	
	@Autowired
	private ProposalRepository repository;
	
	@Autowired
	private ApiCards apiCards;
	
	private String json(ProposalRequestTest proposal) throws JsonProcessingException {
		return jsonMaper.writeValueAsString(proposal);
	}
	
	private void proposalCards() {
		
		List<Proposal> proposals = repository.findByStatusAndIdCard(StatusProposal.ELEGIVEL, null);
		
		if(proposals.isEmpty()) return;
		
		for (Proposal proposal : proposals) {
			System.out.println(proposal.getName());
			ApiNewCardOut card = proposal.reqCard();
			
			try {
				
				ApiNewCardIn nCard = apiCards.newCard(card);
				System.out.println(nCard.getIdCard());
				proposal.addCard(nCard);
				
			} catch (FeignException e) {
				return;
			}
				
			repository.save(proposal);	
		}
	}
	
	@Test
	public void testeAddCartao() throws Exception {
		ProposalRequestTest request = new ProposalRequestTest("41126609013", "lucaslhc@gmail.com", "Lucas Henrrique", "rua coronel 2290", new BigDecimal(10000.00));
		String requestJson = json(request);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/proposal/new").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		
		proposalCards();
		
		Proposal proposal = manager.createQuery("from Proposal where document = :document", Proposal.class)
				.setParameter("document", request.getDocument()).getSingleResult();
		
		Assertions.assertNotEquals(proposal.getIdCard(), null);
		
	}
}
