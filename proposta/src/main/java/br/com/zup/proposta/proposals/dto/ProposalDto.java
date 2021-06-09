package br.com.zup.proposta.proposals.dto;

import java.math.BigDecimal;

import br.com.zup.proposta.proposals.status.StatusProposal;

public class ProposalDto {

	private String document;
	private String email;
	private String name;
	private String adress;
	private BigDecimal salary;
	private StatusProposal status;
	private String idCard;
	
	public ProposalDto() {
	}

	public ProposalDto(String document, String email, String name, String adress, BigDecimal salary,
			StatusProposal status, String idCard) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.adress = adress;
		this.salary = salary;
		this.status = status;
		this.idCard = idCard;
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
	
}