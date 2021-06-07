package br.com.zup.proposta.proposals.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.client.Solicitation;
import br.com.zup.proposta.client.dto.SolicitationIn;
import br.com.zup.proposta.proposals.entity.Proposal;
import br.com.zup.proposta.proposals.form.ProposalForm;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private Solicitation solicitation;
	
	@PostMapping("/new")
	@Transactional
	public ResponseEntity<?> newProposal(@RequestBody @Valid ProposalForm form, UriComponentsBuilder uriBuilder) {
		
		Proposal proposal = form.toModel();
		SolicitationIn status = solicitation.approval(proposal.solicitation());
		proposal.attStatus(status);
		manager.persist(proposal);
		
		URI proposalUri = uriBuilder.path("/proposal/{id}").build(proposal.getId());
		
		return ResponseEntity.created(proposalUri).build();
	}
}
