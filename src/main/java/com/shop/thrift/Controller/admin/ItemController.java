package com.shop.thrift.Controller.admin;

import com.shop.thrift.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin/item")
//@SessionAttributes(names="item")

public class ItemController {

    @Autowired
    private ItemService itemService;

//    @ModelAttribute("item")
//    public ItemForm getForm(){
//        return new ItemForm();

    @RequestMapping
    public String show(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "admin-item";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        itemService.delete(id);
        return "redirect:/admin/item";
    }


}
