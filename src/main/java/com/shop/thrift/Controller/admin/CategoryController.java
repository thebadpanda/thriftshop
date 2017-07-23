package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Category;
import com.shop.thrift.Filter.BasicFilter;
import com.shop.thrift.Services.CategoryService;
import com.shop.thrift.Validator.CategoryValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import static com.shop.thrift.Util.ParamBuilder.getParams;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/admin/category")
@SessionAttributes(names="category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @InitBinder("category")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CategoryValidator(categoryService));
    }

    @ModelAttribute("category")
    public Category getForm() {
        return new Category();
    }

    @ModelAttribute("filter")
    public BasicFilter getFilter(){
        return new BasicFilter();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
        model.addAttribute("page", categoryService.findAll(filter, pageable));
        return "admin-category";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
        categoryService.delete(id);
        return "redirect:/admin/category"+getParams(pageable, filter);
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        model.addAttribute("category",categoryService.findOne(id));
        model.addAttribute("page", categoryService.findAll(filter, pageable));
        return "admin-category";
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute("category") @Valid Category category, BindingResult br, SessionStatus status, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter) {
        if (br.hasErrors()) {
            model.addAttribute("page", categoryService.findAll(filter, pageable));
            return "admin-category";
        }
        categoryService.save(category);
        status.setComplete();
        return "redirect:/admin/category"+getParams(pageable, filter);
    }

}