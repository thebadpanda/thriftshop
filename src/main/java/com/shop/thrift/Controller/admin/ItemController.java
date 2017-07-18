package com.shop.thrift.Controller.admin;

import com.shop.thrift.Editor.ColorEditor;
import com.shop.thrift.Editor.SizeEditor;
import com.shop.thrift.Editor.SubcategoryEditor;
import com.shop.thrift.Entity.Color;
import com.shop.thrift.Entity.Size;
import com.shop.thrift.Entity.Subcategory;
import com.shop.thrift.Form.ItemForm;
import com.shop.thrift.Services.*;
import com.shop.thrift.Validator.ItemValidator;

import static com.shop.thrift.Util.ParamBuilder.getParams;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

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



@Controller
@RequestMapping("/admin/item")
@SessionAttributes(names="item")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

//    @Autowired
//    private WeightService weightService;


    @InitBinder("item")
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Subcategory.class, new SubcategoryEditor(subcategoryService));
        binder.registerCustomEditor(Color.class, new ColorEditor(colorService));
        binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
        //binder.registerCustomEditor(Weight.class, new WeightEditor(weightService));
        binder.setValidator(new ItemValidator());
    }

    @ModelAttribute("item")
    public ItemForm getForm() {
        return new ItemForm();
    }

    @RequestMapping
    public String show(Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", itemService.findAll(pageable));
        return "admin-item";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id, @PageableDefault Pageable pageable) {
        itemService.delete(id);
        return "redirect:/admin/item"+getParams(pageable);
    }

    @RequestMapping("add/{id}")
    public String showAdd(@PathVariable int id, Model model, @PageableDefault Pageable pageable) {
        model.addAttribute("page", itemService.findAll());
        model.addAttribute("subcategory", subcategoryService.findOne(id));
        model.addAttribute("color", colorService.findAll());
        model.addAttribute("size", sizeService.findAll());
        // model.addAttribute("weight", WeightService.)
        return "admin-item";
    }

    @RequestMapping(method = POST)
    public String save(@ModelAttribute("item") @Valid ItemForm item, BindingResult br, Model model,
                       SessionStatus sessionStatus, @PageableDefault Pageable pageable) {
        if (br.hasErrors()) {
            model.addAttribute("page", itemService.findAll());
            model.addAttribute("subcategory", item.getSubcategory());
            model.addAttribute("color", colorService.findAll());
            model.addAttribute("size", sizeService.findAll());
            // model.addAttribute("weight", WeightService.)
            return "admin-item";
        }
        itemService.save(item);
        sessionStatus.setComplete();
        return "redirect:/admin/item"+getParams(pageable);

    }
}
