package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Basket;
import com.shop.thrift.Services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/basket")
@SessionAttributes(names="basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @ModelAttribute
    public Basket getBasket(){
        return new Basket();
    }

    @RequestMapping
    public String show(Model model){
        model.addAttribute("baskets", basketService.findAll());
        return "admin-basket";

    }

    @RequestMapping("/delete/{id}")
    public void delete(int id){
        basketService.delete(id);
    }

    @RequestMapping(method=POST)
    public String save(@ModelAttribute("basket") Basket basket, SessionStatus status){
        basketService.save(basket);
        status.setComplete();
        return "redirect:/admin/basket";
    }


}
