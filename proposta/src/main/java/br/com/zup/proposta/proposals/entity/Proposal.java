package br.com.zup.proposta.proposals.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

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
}
