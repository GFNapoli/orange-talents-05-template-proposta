package br.com.zup.proposta.proposal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import br.com.zup.proposta.dto.ProposalRequestTest;
import br.com.zup.proposta.proposals.entity.Proposal;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
public class ProposalTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonMaper;
	
	@Autowired
	EntityManager manager;
	
	private String json(ProposalRequestTest proposal) throws JsonProcessingException {
		return jsonMaper.writeValueAsString(proposal);
	}
	
	@Test
	public void deveCriarNovaProposta() throws Exception {
		
		ProposalRequestTest request = new ProposalRequestTest("41126609013", "lucaslhc@gmail.com", "Lucas Henrrique", "rua coronel 2290", new BigDecimal(10000.00));
		String requestJson = json(request);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/proposal/new").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		
		List<Proposal> proposal = manager.createQuery("from Proposal where document = :document", Proposal.class)
				.setParameter("document", request.getDocument()).getResultList();
		
		Assertions.assertTrue(proposal.size() == 1);
		Assertions.assertAll(() -> assertEquals(proposal.get(0).getName(), request.getName()),
				() -> assertEquals(proposal.get(0).getEmail(), request.getEmail()),
				() -> assertEquals(proposal.get(0).getAdress(), request.getAdress()),
				() -> assertEquals(proposal.get(0).getDocument(), request.getDocument()),
				() -> assertEquals(proposal.get(0).getSalary(), request.getSalary()));
	}
	
	@Test
	public void daveApenasExistirUmaProposatPorCliente() throws Exception {
		
		ProposalRequestTest request = new ProposalRequestTest("41126609013", "lucaslhc@gmail.com", "Lucas Henrrique", "rua coronel 2290", new BigDecimal(10000.00));
		String requestJson = json(request);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/proposal/new").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/proposal/new").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
		
		List<Proposal> proposal = manager.createQuery("from Proposal where document = :document", Proposal.class)
				.setParameter("document", request.getDocument()).getResultList();
		
		Assertions.assertTrue(proposal.size() == 1);
	}
	
	@Test
	public void deveConsultarOsDadosDoCliente() throws Exception {
		
		ProposalRequestTest request = new ProposalRequestTest("41126609013", "lucaslhc@gmail.com", "Lucas Henrrique", "rua coronel 2290", new BigDecimal(10000.00));
		String requestJson = json(request);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/proposal/new").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
			.andExpect(MockMvcResultMatchers.status().isCreated());
		
		Proposal proposal = manager.createQuery("from Proposal where document = :document", Proposal.class)
				.setParameter("document", request.getDocument()).getSingleResult();
		
		mockMvc.perform(MockMvcRequestBuilders.get("/proposal/"+proposal.getId()))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
}
