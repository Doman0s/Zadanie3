package pl.edu.wszib.food.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.food.shop.exceptions.AuthException;
import pl.edu.wszib.food.shop.exceptions.LoginUsedException;
import pl.edu.wszib.food.shop.model.Ruser;
import pl.edu.wszib.food.shop.service.IAuthService;
import pl.edu.wszib.food.shop.session.Session;
import pl.edu.wszib.food.shop.validators.LoginValidator;
import pl.edu.wszib.food.shop.validators.RegisterValidator;

import javax.annotation.Resource;

@Controller
public class AuthController {
    @Autowired
    IAuthService authService;

    @Resource
    Session session;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("logged", this.session.isLogged());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        try {
            LoginValidator.validateLogin(login);
            LoginValidator.validatePassword(password);
        } catch (AuthException e) {
            return "redirect:/login";
        }

        this.authService.login(login, password);

        if(this.session.isLogged()) {
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.session.getCart().clearOrders();
        this.session.setUser(null);
        return "redirect:/main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("logged", this.session.isLogged());
        model.addAttribute("ruser", new Ruser());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute Ruser Ruser) {
        try {
            RegisterValidator.validateName(Ruser.getName());
            RegisterValidator.validateSurname(Ruser.getSurname());
            LoginValidator.validateLogin(Ruser.getLogin());
            LoginValidator.validatePassword(Ruser.getPassword());
            checkPasswords(Ruser.getPassword(), Ruser.getSecondPassword());
            this.authService.register(Ruser);
        } catch (AuthException | LoginUsedException e) {
            return "redirect:/register";
        }

        return "redirect:/main";
    }

    private void checkPasswords(String pass1, String pass2) {
        if(pass1 == null || !pass1.equals(pass2)) {
            throw new AuthException();
        }
    }
}