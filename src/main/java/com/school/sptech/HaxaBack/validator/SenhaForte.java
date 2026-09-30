package com.school.sptech.HaxaBack.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SenhaForteValidator.class)
public @interface SenhaForte {

    String message() default
            "senha deve possuir pelo menos 8 caracteres, uma letra e um número";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
