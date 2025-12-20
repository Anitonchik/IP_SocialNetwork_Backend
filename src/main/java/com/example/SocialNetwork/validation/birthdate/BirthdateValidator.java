package com.example.SocialNetwork.validation.birthdate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class BirthdateValidator implements ConstraintValidator<Birthdate, String> {

    private static final String DATE_FORMAT_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

    private static final Pattern PATTERN = Pattern.compile(DATE_FORMAT_REGEX);

    private int minAge;
    private int maxAge;

    @Override
    public void initialize(Birthdate constraintAnnotation) {
        this.minAge = constraintAnnotation.min();
        this.maxAge = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String birthDate, ConstraintValidatorContext context) {
        if (birthDate == null || birthDate.isBlank()) {
            return false;
        }

        if (!PATTERN.matcher(birthDate).matches()) {
            return false;
        }

        final LocalDate today = LocalDate.now();
        final LocalDate date = LocalDate.parse(birthDate);
        Period period = Period.between(date, today);
        int age = period.getYears();

        return age >= minAge && age <= maxAge;
    }
}
