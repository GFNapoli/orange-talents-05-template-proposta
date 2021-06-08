package br.com.zup.proposta.proposals.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;

import com.sun.istack.NotNull;

import br.com.zup.proposta.client.dto.ApiNewCardIn;
import br.com.zup.proposta.client.dto.ApiNewCardOut;
import br.com.zup.proposta.client.dto.SolicitationIn;
import br.com.zup.proposta.client.dto.SolicitationOut;
import br.com.zup.proposta.exception.ProposalRequestException;
import br.com.zup.proposta.proposals.status.StatusProposal;

@Entity
public class Proposal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String document;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String adress;
	
	@NotNull
	@Positive
	private BigDecimal salary;
	private StatusProposal status = StatusProposal.NAO_ELEGIVEL;
	private String idCard = null;

	public Proposal() {
	}

	public Proposal(@NotBlank String document, @NotBlank @Email String email, @NotBlank String name,
			@NotBlank String adress, @Positive BigDecimal salary) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.adress = adress;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public String getDocument() {
		return document;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public BigDecimal getSalary() {
		return salary;
	}
	
	public StatusProposal getStatus() {
		return status;
	}
	
	public String getIdCard() {
		return idCard;
	}

	public SolicitationOut solicitation() {
		
		return new SolicitationOut(document, name, String.valueOf(id));
	}

	public void attStatus(SolicitationIn status) {
		
		if(status.getResultadoSolicitacao().equalsIgnoreCase("SEM_RESTRICAO")) {
			this.status = StatusProposal.ELEGIVEL;
		}else if(status.getResultadoSolicitacao().equalsIgnoreCase("COM_RESTRICAO")) {
			return;
		}else {
			throw new ProposalRequestException("Status da solicitação não compativel", HttpStatus.BAD_GATEWAY);
		}
	}

	public ApiNewCardOut reqCard() {
		return new ApiNewCardOut(document, name, String.valueOf(id));
	}
	
	public void addCard(ApiNewCardIn newCard) {
		this.idCard = newCard.getIdCard();
	}
}
