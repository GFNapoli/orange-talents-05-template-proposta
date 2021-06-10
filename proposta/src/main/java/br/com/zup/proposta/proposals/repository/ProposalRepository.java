package br.com.zup.proposta.proposals.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.zup.proposta.proposals.entity.Proposal;
import br.com.zup.proposta.proposals.status.StatusProposal;

public interface ProposalRepository extends CrudRepository<Proposal, Long>{

	List<Proposal> findByStatusAndCard(StatusProposal elegivel, String idCard);

}
