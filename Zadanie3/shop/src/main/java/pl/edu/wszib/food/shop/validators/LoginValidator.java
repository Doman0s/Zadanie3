package pl.edu.wszib.food.shop.validators;

import pl.edu.wszib.food.shop.exceptions.AuthException;

public class LoginValidator {
    public static void validateLogin(String login) {
        if(login == null || login.length() <= 1) {
            throw new AuthException();
        }
    }

    public static void validatePassword(String password) {
        if(password == null || password.length() <= 1) {
            throw new AuthException();
        }
    }
}
