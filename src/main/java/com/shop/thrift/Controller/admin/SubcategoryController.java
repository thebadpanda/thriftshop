package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/admin/subcategory")
@SessionAttributes(names="subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @ModelAttribute
    public Subcategory getSubcacegory(){
        return new Subcategory();
    }

    @RequestMapping
    public String show(Model model){
        model.addAttribute("subcategories", subcategoryService.findAll());
        return "admin-subcategory";
    }

    @RequestMapping("/delete/{id}")
    public String delete(int id){
        subcategoryService.delete(id);
        return "redirect:/admin/subcategories";
    }



}
