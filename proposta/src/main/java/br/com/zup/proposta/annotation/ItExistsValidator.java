package br.com.zup.proposta.annotation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class ItExistsValidator implements ConstraintValidator<ItExists, Object>{

	private String domainAttribute;
	private Class<?> klass;
	
	@Autowired
	private EntityManager manager;
	
	@Override
	public void initialize(ItExists parm) {
		this.domainAttribute = parm.fieldName();
		this.klass = parm.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1, "Não foi encontrado "+klass+" com o atributo "+domainAttribute+" = "+value);
		
		return !list.isEmpty();
	}

}
