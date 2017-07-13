package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Color;
import com.shop.thrift.Services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin/color")
@SessionAttributes(names="color")
public class ColorController {

    @Autowired
    ColorService colorService;

    @ModelAttribute
    public Color getColor(){
        return new Color();
    }

    @RequestMapping
    public String show(Model model){
        model.addAttribute("colors", colorService.findAll());
        return "admin-color";
    }



}