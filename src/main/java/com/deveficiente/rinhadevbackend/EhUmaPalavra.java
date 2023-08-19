package com.deveficiente.rinhadevbackend;

import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Constraint(validatedBy = {EhUmaPalavraValidator.class})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE }) 
@Retention(RUNTIME)
public @interface EhUmaPalavra {
	String message() default "{com.deveficiente.rinhadevbackend.EhUmaPalavra.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
