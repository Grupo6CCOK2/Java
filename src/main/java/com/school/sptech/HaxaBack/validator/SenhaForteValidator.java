package com.school.sptech.HaxaBack.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaForteValidator
        implements ConstraintValidator<SenhaForte, String> {

    @Override
    public boolean isValid(
            String senha,
            ConstraintValidatorContext context) {

        if (senha == null) {
            return true;
        }

        return senha.length() >= 6
                && senha.matches(".*[A-Za-z].*")
                && senha.matches(".*\\d.*");
    }
}
