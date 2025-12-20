package com.example.SocialNetwork.validation.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class RussianPhoneValidator implements ConstraintValidator<RussianPhone, String> {

    private static final String RUSSIAN_PHONE_REGEX = "^(\\+7|7|8)?[489]\\d{9}$";

    private static final Pattern PATTERN = Pattern.compile(RUSSIAN_PHONE_REGEX);

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null || phone.isBlank()) {
            return false;
        }

        return PATTERN.matcher(phone).matches();
    }
}
