package com.deveficiente.rinhadevbackend;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//preciso implementar a interface de validacao da bean validation

public class EhUmaPalavraValidator implements ConstraintValidator<EhUmaPalavra, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //o padrao da bean validation pede para ser bem especifico na validacao
        if(value == null) return true;

        return value.chars().anyMatch(Character::isLetter);
       
    }
 
    
}
