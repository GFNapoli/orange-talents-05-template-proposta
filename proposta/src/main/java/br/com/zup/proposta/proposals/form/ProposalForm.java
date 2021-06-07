package br.com.zup.proposta.proposals.form;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import br.com.zup.proposta.annotation.DocumentValidation;
import br.com.zup.proposta.proposals.entity.Proposal;

public class ProposalForm {

	@NotBlank
	@DocumentValidation
	private String document;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String adress;
	
	@Positive
	@NotNull
	private BigDecimal salary;

	public ProposalForm() {
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Proposal toModel() {
		return new Proposal(document, email, name, adress, salary);
	}
	
	
}
