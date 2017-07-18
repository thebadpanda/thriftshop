package com.shop.thrift.Controller.admin;


import com.shop.thrift.Entity.Subcategory;
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

    @InitBinder("subcategory")
    protected void initBinder(WebDataBinder binder) {
        //binder.registerCustomEditor(Category.class, new CategoryEditor(categoryService));
        binder.setValidator(new SubcategoryValidator(subcategoryService));
    }

    @ModelAttribute("subcategory")
    public Subcategory getForm(){
        return new Subcategory();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable){
        model.addAttribute("page", subcategoryService.findAll(pageable));
        return "admin-subcategory";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable){
        subcategoryService.delete(id);
        return "redirect:/admin/subcategories"+getParams(pageable);
    }

    @RequestMapping("add/{id}")
    public String showAdd(@PathVariable int id, Model model, @PageableDefault Pageable pageable) {
          model.addAttribute("page", subcategoryService.findAll());
//        model.addAttribute("item", itemService.findAll());
//        model.addAttribute("color", colorService.findAll());
//        model.addAttribute("size", sizeService.findAll());
//             model.addAttribute("weight", WeightService.)
        return "admin-subcategory";
    }
        @RequestMapping(method = POST)
        public String save (@ModelAttribute("subcategory") @Valid Subcategory subcategory, BindingResult
        br, SessionStatus status, Model model, @PageableDefault Pageable pageable){
            if (br.hasErrors()) {
                model.addAttribute("subcategories", subcategoryService.findAll());
                return "admin-subcategory";
            }
            subcategoryService.save(subcategory);
            status.setComplete();
            return "redirect:/admin/subcategory"+getParams(pageable);
        }


}

