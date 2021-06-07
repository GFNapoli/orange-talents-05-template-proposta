package br.com.zup.proposta.annotation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import br.com.zup.proposta.exception.ProposalRequestException;

public class UniqueValidator implements ConstraintValidator<Unique, Object>{

	private String domainAttribute;
	private Class<?> klass;
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public void initialize(Unique params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value")
				.setParameter("value", value);
		
		List<?> result = query.getResultList();
		
		if(result.size() >= 1) throw new ProposalRequestException("Dados já cadastrados", HttpStatus.UNPROCESSABLE_ENTITY);
		
		return result.isEmpty();
	}

}
