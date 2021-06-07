package br.com.zup.proposta.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DocumentValidationValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentValidation {

	String message() default "Documento invalido!";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
