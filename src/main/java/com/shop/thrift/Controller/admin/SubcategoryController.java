package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.dto.Filter.BasicFilter;
import com.shop.thrift.Services.CategoryService;
import com.shop.thrift.Services.SubcategoryService;
import com.shop.thrift.Validator.SubcategoryValidator;

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
@RequestMapping("/admin/subcategory")
@SessionAttributes(names="subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private CategoryService categoryService;

    @InitBinder("subcategory")
    protected void initBinder(WebDataBinder binder) {
        //binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.setValidator(new SubcategoryValidator(subcategoryService));
    }

    @ModelAttribute("subcategory")
    public Subcategory getForm(){
        return new Subcategory();
    }

    @ModelAttribute("filter")
    public BasicFilter getFilter(){
        return new BasicFilter();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        model.addAttribute("page", subcategoryService.findAll(filter, pageable));
        return "admin-subcategory";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        subcategoryService.delete(id);
        return "redirect:/admin/subcategory"+getParams(pageable, filter);
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BasicFilter filter){
        model.addAttribute("subcategory",subcategoryService.findOne(id));
        model.addAttribute("page", subcategoryService.findAll(filter, pageable));
        return "admin-subcategory";
    }

    @RequestMapping("add/{id}")
    public String showAdd(@PathVariable int id, Model model, @PageableDefault Pageable pageable,@ModelAttribute("filter") BasicFilter filter) {
          model.addAttribute("page", subcategoryService.findAll(filter, pageable));
          model.addAttribute("category", categoryService.findOne(id));
        return "admin-subcategory";
    }
        @RequestMapping(method = POST)
        public String save (@ModelAttribute("subcategory") @Valid Subcategory subcategory, BindingResult
        br, SessionStatus status, Model model, @PageableDefault Pageable pageable,@ModelAttribute("filter") BasicFilter filter){
            if (br.hasErrors()) {
                model.addAttribute("page", subcategoryService.findAll(filter, pageable));
                model.addAttribute("category", subcategory.getCategory());
                return "admin-subcategory";
            }
            subcategoryService.save(subcategory);
            status.setComplete();
            return "redirect:/admin/subcategory"+getParams(pageable, filter);
        }


}

