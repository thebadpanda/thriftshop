package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Category;
import com.shop.thrift.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes(names="category")

public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("category")
    public Category getCategory(){
        return new Category();
    }

    @RequestMapping
    public String show (Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "admin-category";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        categoryService.delete(id);
        return "redirect:/admin/category";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model){
        model.addAttribute("form", categoryService.findOne(id));
        model.addAttribute("categories", categoryService.findAll());
        return "admin-category";

    }

    @RequestMapping(method=POST)
    public String save(@ModelAttribute("form") Category category, SessionStatus status){
        categoryService.save(category);
        status.setComplete();
        return "redirect:/admin/category";
    }



}