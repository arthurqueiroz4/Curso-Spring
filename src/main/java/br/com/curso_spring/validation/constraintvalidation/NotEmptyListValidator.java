package br.com.curso_spring.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.curso_spring.validation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value!=null && !value.isEmpty();
	}
	
}
