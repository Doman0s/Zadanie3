package pl.edu.wszib.food.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.food.shop.service.ICartService;
import pl.edu.wszib.food.shop.session.Session;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @Resource
    Session session;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart", this.session.getCart());
        model.addAttribute("result", this.session.getCart().getCalculatedSum());
        model.addAttribute("logged", this.session.isLogged());
        return "cart";
    }

    @RequestMapping(value = "/add/{productId}")
    public String addToCart(@PathVariable Integer productId) {
        this.cartService.addToCart(productId);
        return "redirect:/main";
    }

    @RequestMapping(value = "/delete/{productId}")
    public String deleteFromCart(@PathVariable Integer productId) {
        this.cartService.deleteFromCart(productId);
        return "redirect:/cart";
    }
}
