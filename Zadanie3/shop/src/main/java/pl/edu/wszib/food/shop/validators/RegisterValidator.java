package pl.edu.wszib.food.shop.validators;

import pl.edu.wszib.food.shop.exceptions.RegisterException;

public class RegisterValidator {
    public static void validateName(String name) {
        checkValues(name);
    }

    public static void validateSurname(String surname) {
        checkValues(surname);
    }

    private static void checkValues(String value) {
        if(value == null || value.length() <= 1) {
            throw new RegisterException();
        }
    }
}
