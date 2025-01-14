package br.com.zup.proposta.proposals.async;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zup.proposta.client.ApiCards;
import br.com.zup.proposta.client.dto.ApiNewCardIn;
import br.com.zup.proposta.client.dto.ApiNewCardOut;
import br.com.zup.proposta.proposals.entity.Proposal;
import br.com.zup.proposta.proposals.repository.ProposalRepository;
import br.com.zup.proposta.proposals.status.StatusProposal;
import feign.FeignException;

@Component
public class ProposalCards {

	@Autowired
	private ProposalRepository repository;
	
	@Autowired
	private ApiCards apiCards;
	
	@Scheduled(fixedDelayString = "${time.assync}")
	protected void proposalCards() {
		
		List<Proposal> proposals = repository.findByStatusAndCard(StatusProposal.ELEGIVEL, null);
		
		if(proposals.isEmpty()) return;
		
		for (Proposal proposal : proposals) {
			ApiNewCardOut card = proposal.reqCard();
			
			try {
				
				ApiNewCardIn nCard = apiCards.newCard(card);
				proposal.addCard(nCard);
				
			} catch (FeignException e) {
				return;
			}
				
			repository.save(proposal);	
		}
	}
}
