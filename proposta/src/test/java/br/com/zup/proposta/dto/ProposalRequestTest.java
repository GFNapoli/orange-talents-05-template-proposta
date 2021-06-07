package br.com.zup.proposta.dto;

import java.math.BigDecimal;

public class ProposalRequestTest {

	private String document;
	private String email;
	private String name;
	private String adress;
	private BigDecimal salary;

	public ProposalRequestTest(String document, String email, String name, String adress, BigDecimal salary) {
		this.document = document;
		this.email = email;
		this.name = name;
		this.adress = adress;
		this.salary = salary;
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

}
