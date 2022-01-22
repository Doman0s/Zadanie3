package pl.edu.wszib.food.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.food.shop.service.IProductService;
import pl.edu.wszib.food.shop.session.Session;

import javax.annotation.Resource;

@Controller
public class CommonController {
    @Autowired
    IProductService productService;

    @Resource
    Session session;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("products", this.productService.getAllProducts());
        model.addAttribute("logged", this.session.isLogged());
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        model.addAttribute("logged", this.session.isLogged());
        return "contact";
    }
}
