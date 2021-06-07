package br.com.zup.proposta.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentValidationValidator implements ConstraintValidator<DocumentValidation, String>{

	@Override
	public void initialize(DocumentValidation constraintAnnotation) {}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String document = converter(value);
		if(document.length() == 11){
			return cpf(document);
		}
		
		return cnpj(document);
	}

	public String converter(String cpfCnpj) {
		String documentNumber = cpfCnpj.replace(".", "");
		documentNumber = documentNumber.replace("-", "");
		documentNumber = documentNumber.replace("/", "");
		
		return documentNumber;
	}
	
	public boolean cpf(String cpf) {
		
		try {
			Long.parseLong(cpf);
		} catch (Exception e) {
			return false;
		}
		
		int validType1=0, validType2=0, rest=0;
		int digitCpf;
		
		for(int i = 0; i<cpf.length()-2; i++) {
			digitCpf = Integer.valueOf(cpf.substring(i, i+1)).intValue();
			
			validType1 += digitCpf*(10-i);
			validType2 += digitCpf*(11-i); 
			
		}
		
		rest = validType1 % 11;
		
		if(rest < 2 ) {
			validType1 = 0;
		}else {
			validType1 = 11 - rest;
		}
		
		validType2 += validType1*2;
		rest = validType2 % 11;
		if(rest < 2) {
			validType2 = 0;
		}else {
			validType2 = 11-rest;
		}
		
		String verifyingDigit = cpf.substring(cpf.length()-2, cpf.length());
		String calculatedType = String.valueOf(validType1) + String.valueOf(validType2);
		
		return verifyingDigit.equals(calculatedType);	
	}
	
	public boolean cnpj(String cnpj) {
		
		try {
			Long.parseLong(cnpj);
		} catch (Exception e) {
			return false;
		}
		
		int validType1=0, validType2=0, rest=0;
		int digitCnpj;
		int weight=5;
		
		for(int i = 0; i < cnpj.length()-2; i++) {
			digitCnpj = Integer.valueOf(cnpj.substring(i, i+1)).intValue();
			
			validType1 += digitCnpj* weight;
			weight--;
			
			if(weight ==1) weight = 9;
		}
		
		rest = validType1 %11;
		
		if(rest < 2) {
			validType1 = 0;
		}else {
			validType1 = 11 - rest;
		}
		
		weight = 6;
		
		for(int i = 0; i < cnpj.length()-1; i++) {
			digitCnpj = Integer.valueOf(cnpj.substring(i, i+1)).intValue();
			
			validType2 += digitCnpj * weight;
			weight--;
			
			if(weight ==1) weight = 9;
		}
		
		rest = validType2 % 11;
		
		if(rest <2) {
			validType2 = 0;
		}else {
			validType2 = 11 - rest;
		}
		
		String verifyingDigit = cnpj.substring(cnpj.length()-2, cnpj.length());
		String calculatedType = String.valueOf(validType1) + String.valueOf(validType2);
		
		return verifyingDigit.equals(calculatedType);
		
	}

}
