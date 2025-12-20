package com.example.SocialNetwork.validation.phone;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RussianPhoneValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface RussianPhone {
    String message() default "Invalid Russian phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
