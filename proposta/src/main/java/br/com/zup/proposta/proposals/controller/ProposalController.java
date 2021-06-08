package br.com.zup.proposta.proposals.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.proposta.client.Solicitation;
import br.com.zup.proposta.client.dto.SolicitationIn;
import br.com.zup.proposta.exception.ProposalRequestException;
import br.com.zup.proposta.proposals.dto.ProposalDto;
import br.com.zup.proposta.proposals.entity.Proposal;
import br.com.zup.proposta.proposals.form.ProposalForm;
import br.com.zup.proposta.proposals.repository.ProposalRepository;

@RestController
@RequestMapping("/proposal")
public class ProposalController {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private ProposalRepository repository;
	
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
	
	@GetMapping("/{id}")
	public ResponseEntity<ProposalDto> consultProposal(@PathVariable Long id){
		
		Proposal proposal = repository.findById(id).orElseThrow(
				() -> new ProposalRequestException("Proposta não encontrada!", HttpStatus.NOT_FOUND));
		
		return ResponseEntity.ok(proposal.proposalOut());
	}
}
