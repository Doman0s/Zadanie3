package pl.edu.wszib.food.shop.service;

import pl.edu.wszib.food.shop.model.Ruser;

public interface IAuthService {
    void login(String login, String password);
    void register(Ruser registerUser);
}
