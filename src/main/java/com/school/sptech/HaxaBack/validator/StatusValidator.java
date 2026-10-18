package com.school.sptech.HaxaBack.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<Status, String> {


    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {

        if (status == null){
            return true;
        }

        return status.equalsIgnoreCase("pendente")
                || status.equalsIgnoreCase("confirmado")
                || status.equalsIgnoreCase("concluido")
                || status.equalsIgnoreCase("cancelado");
    }
}
